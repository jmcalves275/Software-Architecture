import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by André on 25/02/2015.
 */
public class Splitter extends FilterFramework {

    private List<PipedOutputStream> outputPipes = new ArrayList<PipedOutputStream>();

    PipedOutputStream getPort(){
        PipedOutputStream out = new PipedOutputStream();
        outputPipes.add(out);
        return out;
    }


    @Override
    void WriteFilterOutputPort(byte datum)
    {
        try
        {
            for(PipedOutputStream pipe : outputPipes){
                pipe.write((int) datum);
                pipe.flush();
            }


        } // try

        catch( Exception Error )
        {
            System.out.println("\n" + this.getName() + " Pipe write error::" + Error );

        } // catch

        return;

    } // WriteFilterPort

    public void run()
    {

        int bytesread = 0;					// Number of bytes read from the input file.
        int byteswritten = 0;				// Number of bytes written to the stream.
        byte databyte = 0;					// The byte of data read from the file


        System.out.print( "\n" + this.getName() + "::Splitter Reading ");

        while (true)
        {

            try
            {
                databyte = ReadFilterInputPort();
                bytesread++;
                WriteFilterOutputPort(databyte);
                byteswritten++;

            } // try

            catch (EndOfStreamException e)
            {
                //e.printStackTrace();
                ClosePorts();
                System.out.print( "\n" + this.getName() + "::splitter Exiting; bytes read: " + bytesread + " bytes written: " + byteswritten );
                break;

            } // catch

        } // while

    } // run

}
