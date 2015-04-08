import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.LinkedList;

/**
 * Created by André on 25/02/2015.
 */
public class SourceMerger extends FilterFramework {

    private LinkedList<PipedInputStream> inputPipes = new LinkedList<PipedInputStream>();
    private LinkedList<FilterFramework> inputFilters = new LinkedList<FilterFramework>();

    private LinkedList<byte[]> frameList = new LinkedList<byte[]>();

    private int[] bytesRead = new int[100];
    private boolean[] aliveStatus = new boolean[100];
    private int sumBytesRead = 0;
    private int byteswritten = 0;



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
            System.out.println( "\n" + this.getName() + " SourceMerger error connecting::"+ Error );

        } // catch

    } // Connect

    void Connect( FilterFramework Filter, PipedOutputStream out )
    {
        try {
            PipedInputStream p = new PipedInputStream();
            inputPipes.add(p);
            p.connect(out);
            inputFilters.add(Filter);
            frameList.add(new byte[FRAME_SIZE]);
            aliveStatus[inputPipes.size()] = true;

        } // try

        catch( Exception Error )
        {
            System.out.println( "\n" + this.getName() + " SourceMerger error connecting::"+ Error );

        } // catch

    } // Connect



    boolean frameCompleted(int pipeIndex){
        if(bytesRead[pipeIndex] < FRAME_SIZE)
            return false;

        if(bytesRead[pipeIndex] == FRAME_SIZE)
            return true;

        return true;
    }

    void readFromPipes() throws EndOfStreamException {

        for(int i = 0; i < inputPipes.size(); i++){
            if(bytesRead[i] < FRAME_SIZE && pipeContainsData(i)){
                byte databyte = 0;
                databyte = ReadFilterInputPort(i);


                frameList.get(i)[bytesRead[i] - 1] = databyte;
            }

        }
    }

    public void run(){

        System.out.print( "\n" + this.getName() + "::SourceMerger Reading ");

        while (true){

            try{

                for(int i = 0; i < inputPipes.size(); i++){
                    if(frameCompleted(i)){
                        writeFrameToOutput(frameList.get(i));
                        clearFrameData(i);
                    }
                }

                readFromPipes();


            }

            catch (EndOfStreamException e)
            {
                ClosePorts();
                System.out.print( "\n" + this.getName() + "::SourceMerger Exiting; bytes read: " + sumBytesRead + " bytes written: " + byteswritten );
                break;

            }

        }

    }

    private void clearFrameData(int frameIndex){
        bytesRead[frameIndex] = 0;

        byte[] a = frameList.get(frameIndex);
        for(int i = 0;  i < FRAME_SIZE; i++)
            a[i] = 0;

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
            bytesRead[pipeIndex]++;
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
                    System.out.println(this.getName() + "::SourceMerger input "+i+" closed.");
                    inputPipes.get(i).close();
                }
                i++;
            }

        } catch (Exception Error) {
            System.out.println("\n" + this.getName() + " ClosePorts error::" + Error);

        } // catch

    }
}
