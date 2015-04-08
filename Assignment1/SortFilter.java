/******************************************************************************************************************
 * File:SinkFilter.java
 * Course: 17655
 * Project: Assignment 1
 * Copyright: Copyright (c) 2003 Carnegie Mellon University
 * Versions:
 *	1.0 November 2008 - Sample Pipe and Filter code (ajl).
 *
 * Description:
 *
 * This class serves as an example for using the SinkFilterTemplate for creating a sink filter. This particular
 * filter reads some input from the filter's input port and does the following:
 *
 *	1) It parses the input stream and "decommutates" the measurement ID
 *	2) It parses the input steam for measurments and "decommutates" measurements, storing the bits in a long word.
 *
 * This filter illustrates how to convert the byte stream data from the upstream filterinto useable data found in
 * the stream: namely time (long type) and measurements (double type).
 *
 *
 * Parameters: 	None
 *
 * Internal Methods: None
 *
 ******************************************************************************************************************/

import java.util.*;

public class SortFilter extends FilterFramework {

    int operation;
    int byteIndex;

    int MeasurementLength = 8;
    int IdLength = 4;

    ArrayList<byte[]> finalArray = new ArrayList<byte[]>();


    public SortFilter(int operation) {
        this.operation = operation;
        byteIndex = op2index(operation);
    }

    private int op2index(int operation) {
        return (operation + 1) * IdLength + MeasurementLength * operation;
    }


    Calendar timestampA = Calendar.getInstance(), timestampB = Calendar.getInstance();
    private int compare(int index, byte[] arrayA, byte[] arrayB, boolean desc) {
        long a = 0;
        long b = 0;

        double aa, bb;

        for (int i = 0; i < MeasurementLength; i++) {
            a = a | (arrayA[index + i] & 0xFF);

            if (i != MeasurementLength - 1) {
                a = a << 8;
            }

            b = b | (arrayB[index + i] & 0xFF);

            if (i != MeasurementLength - 1) {
                b = b << 8;
            }
        }

        if (operation == 0) {
            timestampA.setTimeInMillis(a);
            timestampB.setTimeInMillis(b);
            if (!desc)
                return timestampA.compareTo(timestampB);
            else
                return timestampA.compareTo(timestampB) * -1;

        }

        aa = Double.longBitsToDouble(a);
        bb = Double.longBitsToDouble(b);


        if (!desc) {
            if (aa < bb)
                return -1;
            if (aa > bb)
                return 1;
        } else {
            if (aa < bb)
                return 1;
            if (aa > bb)
                return -1;
        }

        if (aa == bb)
            return 0;

        return 0;

    }

    private void writeSortedOutput(){
        System.out.println("Sorted array size: " + finalArray.size());
        for (int i = 0; i < finalArray.size(); i++) {
            byte[] temp = finalArray.get(i);
            for (int z = 0; z < (IdLength + MeasurementLength) * 5; z++) {
                WriteFilterOutputPort(temp[z]);
            }

        }
    }


    public void run() {

        byte databyte = 0;                // This is the data byte read from the stream
        int bytesread = 0;                // This is the number of bytes read from the stream

        int i, j = 0, z;                            // This is a loop counter

        /*************************************************************
         *	First we announce to the world that we are alive...
         **************************************************************/

        System.out.print("\n" + this.getName() + "::SortFilter Reading ");

        while (true) {
            try {

                byte[] frame = new byte[FRAME_SIZE];
                for (i = 0; i < FRAME_SIZE; i++) {
                    databyte = ReadFilterInputPort();
                    frame[i] = databyte;
                    bytesread++;


                }


                if (finalArray.size() < 1) {
                    finalArray.add(frame);
                } else {

                    for (i = 0; i < finalArray.size(); i++) {
                        byte[] temp = finalArray.get(i);

                        int cmp = compare(byteIndex, frame, temp, false);

                        if (cmp == 0) {
                            finalArray.add(i, frame);
                        } else if (cmp == -1) {
                            finalArray.add(i, frame);
                        } else if (cmp == 1 && i == finalArray.size() - 1) {
                            finalArray.add(frame);
                        }

                        break;

                    }
                }




            } // try

            catch (EndOfStreamException e) {

                writeSortedOutput();

                ClosePorts();
                System.out.print("\n" + this.getName() + "::SortFilter Exiting; bytes read: " + bytesread + " bytes written: " + byteswritten);
                break;

            } // catch

        } // while

    } // run

} // SingFilter