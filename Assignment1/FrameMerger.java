import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by André on 25/02/2015.
 */
public class FrameMerger extends FilterFramework {

    private List<PipedInputStream> inputPipes = new ArrayList<PipedInputStream>();
    private List<FilterFramework> inputFilters = new ArrayList<FilterFramework>();

    private List<byte[]> frameList = new ArrayList<byte[]>();

    private int[] bytesRead = new int[10];
    private boolean[] aliveStatus = new boolean[10];
    private int sumBytesRead = 0;




    @Override
    void Connect( FilterFramework Filter )
    {
        try {
            PipedInputStream p = new PipedInputStream();
            inputPipes.add(p);
            p.connect(Filter.OutputWritePort);
            inputFilters.add(Filter);
            frameList.add(new byte[FRAME_SIZE]);
            aliveStatus[inputPipes.size()] = true;

        } // try

        catch( Exception Error )
        {
            System.out.println( "\n" + this.getName() + " FrameMerger error connecting::"+ Error );

        } // catch

    } // Connect



    int MeasurementLength = 8;
    int IdLength = 4;

    boolean allFramesCompleted(){
        for(int i = 0; i < inputPipes.size(); i++){
            if(bytesRead[i] < FRAME_SIZE )
                return false;
        }

        return true;
    }

    void readFromPipes() throws EndOfStreamException {

        for(int i = 0; i < inputPipes.size(); i++){
            if(bytesRead[i] < FRAME_SIZE && pipeContainsData(i)){

                byte databyte = 0;
                databyte = ReadFilterInputPort(i);
                frameList.get(i)[bytesRead[i]] = databyte;
                bytesRead[i]++;
            }

        }
    }

    public void run(){

        System.out.print( "\n" + this.getName() + "::FrameMerger Reading ");

        while (true){

            try{

                if(allFramesCompleted()){
                    byte[] mergedFrame = mergeDifferentFrames();
                    writeFrameToOutput(mergedFrame);
                    clearTempData();
                }

                readFromPipes();


            }

            catch (EndOfStreamException e)
            {
                ClosePorts();
                System.out.print( "\n" + this.getName() + "::FrameMerger Exiting; bytes read: " + sumBytesRead + " bytes written: " + byteswritten );
                break;

            }

        }

    }

    private void clearTempData(){
        for(int i = 0;  i < bytesRead.length; i++)
            bytesRead[i] = 0;

        for(byte[] a : frameList){
            for(int i = 0;  i < FRAME_SIZE; i++)
                a[i] = 0;
        }
    }

    private int readId(byte[] array, int index){

        int id = 0;
        for (int i=0; i<IdLength; i++ ){
            id = id | (array[index + i] & 0xFF);

            if (i != IdLength-1)
                id = id << 8;
        }

        return id;
    }

    public byte[] idToByte(int value){
        int i;
        byte[] output = new byte[4];
        for(i = 0; i < 4; i++){
            output[i] = (byte)((value >> ((7 - i) * 8)));

        }

        return output;
    }


    private byte[] mergeDifferentFrames() {

        if(inputPipes.size() == 1)
            return frameList.get(0);

        byte[] finalFrame = new byte[FRAME_SIZE];

        for(int i = 0; i < IdLength + MeasurementLength; i++)
            finalFrame[i] = frameList.get(0)[i];

        for(int i = IdLength+MeasurementLength; i < FRAME_SIZE - MeasurementLength; i+=IdLength+MeasurementLength){

            for(int a = 0; a < frameList.size(); a++){

                int id = readId(frameList.get(a), i);
                byte[] posId = id < 0 ? idToByte(id * -1) : idToByte(id);

                if(id < 0){
                    for (int x = 0; x < IdLength; x++)
                        finalFrame[i + x] = posId[x];
                    for (int x = 0; x < MeasurementLength; x++)
                        finalFrame[i + x + IdLength] = frameList.get(a)[i + x + IdLength];
                    break;
                }

                if(a == frameList.size() - 1){
                    for (int x = 0; x < IdLength; x++)
                        finalFrame[i + x] = posId[x];
                    for (int x = 0; x < MeasurementLength; x++)
                        finalFrame[i + x + IdLength] = frameList.get(a)[i + x + IdLength];
                }

            }
        }


        return finalFrame;
    }

    boolean pipeContainsData(int pipeIndex) throws EndOfStreamException{
        try {
            if (inputPipes.get(pipeIndex).available() == 0 )
            {
                if (EndOfInputStream(pipeIndex))
                {
                    throw new EndOfStreamException("End of input stream reached");
                }

                return false;
            }else {
                return true;
            }
        } catch (EndOfStreamException e) {
            throw e;
        }

        catch( Exception Error ) {
            System.out.println("\n" + this.getName() + " Error in read port wait loop::" + Error);
        }

        return false;
    }

    byte ReadFilterInputPort(int pipeIndex) throws EndOfStreamException
    {
        byte datum = 0;

        try
        {
            datum = (byte)inputPipes.get(pipeIndex).read();
            //System.out.print(Integer.toHexString(datum & 0xFF) + " ");

            sumBytesRead++;
            return datum;

        }

        catch( Exception Error )
        {
            System.out.println( "\n" + this.getName() + " Pipe read error::" + Error );
            return datum;

        }

    }

    private boolean EndOfInputStream(int filterIndex)
    {
        if (inputFilters.get(filterIndex).isAlive())
        {
            return false;

        } else {

            return true;

        }

    }

    @Override
    void ClosePorts() {
        try {
            int i = 0;
            for(FilterFramework inFilter : inputFilters){
                if(aliveStatus[i] && !inFilter.isAlive()){
                    aliveStatus[i] = false;
                    System.out.println(this.getName() + "::FrameMerger"+ i + " input filter closed.");
                    inputPipes.get(i).close();
                }
                i++;
            }

        } catch (Exception Error) {
            System.out.println("\n" + this.getName() + " ClosePorts error::" + Error);

        } // catch

    }
}
