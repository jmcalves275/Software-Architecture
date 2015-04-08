import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/******************************************************************************************************************
 * File:PressureFilter.java
 * Course: Arquitetura de Software
 * Project: Project 1
 * Copyright: Copyright (c) 2003 Carnegie Mellon University
 * Versions:
 *	1.0 February 2015 - Write initial code.
 *
 * Description:
 *
 * This class serves to conver the temperature data from fahrenheit to celcius if recives any other data just passes
 * that information to the next filter.
 *
 * Parameters: 		None
 *
 * Internal Methods:
 *
 *	public void run() - this method must be overridden by this class.
 *
 ******************************************************************************************************************/

public class PressureFilter extends FilterFramework
{
    public void run()
    {

        List<byte[]> frameList = new ArrayList<byte[]>();

        int invalidPoints = 0;

        double maxPressure = 80.0;
        double minPressure = 50.0;

        double lastValid = -1;
        double nextValid = -1;

        byte databyte = 0; //the of the data read from the file


        boolean isfirst = true;

        BufferedWriter bw = null;

        long time = 0;

        int MeasurementLength = 8;		// This is the length of all measurements (including time) in bytes
        int IdLength = 4;				// This is the length of IDs in the byte stream
        long measurement;				// This is the word used to store all measurements - conversions are illustrated.
        int frameSize = 60;
        int id = 3;					    // This is the measurement id
        int idN = -id;
        int firstPressureByteToRead = ((id+1)*IdLength) + (id*8);
        int lastPressureByteToRead = firstPressureByteToRead + MeasurementLength-1;
        int firstPressureIdByteToRead = ((id)*IdLength) + (id*8);
        int lastPressureIdByteToRead = firstPressureIdByteToRead + IdLength-1;
        int firstTimestampByteToRead = 4;
        int lastTimestampByteToRead = 12;
        int i;							// This is a loop counter

        int listIncrementor = 0;

        // Next we write a message to the terminal to let the world know we are alive...

        System.out.print( "\n" + this.getName() + "::Pressure filter");

        while (true)
        {
            try
            {
                /***
                 * Create File
                 */
                FileWriter writeWildpoints = new FileWriter("wildpoints.dat", true);

                bw = new BufferedWriter(writeWildpoints);

                byte[] frame = new byte[frameSize];

                for(int j = 0; j<=frameSize-1; j++)
                {
                    databyte = ReadFilterInputPort();// This is where we read the byte from the stream...

                    frame[j] = databyte;

                    if(j == frameSize-1)
                    {

                        frameList.add(frame);

                    }
                }

                if (frameList.size()!=0){

                    double pressureDouble = 0;


                    for(int x = listIncrementor; x<frameList.size()-1;x++)
                    {
                        byte[] frameAux = frameList.get(x);

                        pressureDouble = Double.longBitsToDouble(readMeasurement(40,48, frameList.get(x)));

                        if(pressureDouble < minPressure || pressureDouble > maxPressure){

                            long timestamp = readMeasurement(firstTimestampByteToRead, lastTimestampByteToRead, frameList.get(x));

                            bw = writeWildPointsToFile(timestamp, pressureDouble, bw);

                            invalidPoints++;
                        }
                        else
                        {
                            lastValid = nextValid;

                            nextValid = pressureDouble;

                            sendFrame(frameList.get(x));

                            if(isfirst)
                            {
                                for(int j = 0; j<invalidPoints; j++)
                                {
                                    //change id to negative
                                    long idNegative, lng; // Long in conversion of double to bytes

                                    idNegative = Double.doubleToLongBits(idN);

                                    for (i = firstPressureIdByteToRead; i < lastPressureIdByteToRead; i++)
                                    {
                                        frameList.get(j)[i] = (byte) ((idNegative >> ((7 - i) * 8)) & 0xFF);
                                    } // for

                                    lng = Double.doubleToLongBits(nextValid); // Convert of mesurement to bytes to send to next filter

                                    for (i = firstPressureByteToRead; i < lastPressureByteToRead; i++)
                                    {
                                        frameList.get(j)[i] = (byte) ((lng >> ((7 - i) * 8)) & 0xFF);
                                    } // for

                                    sendFrame(frameList.get(j));

                                    frameList.remove(j);
                                }
                            }
                            else
                            {
                                for(int j = 0; j<invalidPoints; j++)
                                {
                                    long idNegative, lng; // Long in conversion of double to bytes

                                    idNegative = Double.doubleToLongBits(idN);

                                    for (i = firstPressureIdByteToRead; i < lastPressureIdByteToRead; i++)
                                    {
                                        frameList.get(j)[i] = (byte) ((idNegative >> ((7 - i) * 8)) & 0xFF);
                                    } // for

                                    lng = Double.doubleToLongBits((lastValid+nextValid)/2); // Convert of mesurement to bytes to send to next filter

                                    for (i = firstPressureByteToRead; i < lastPressureByteToRead; i++)
                                    {
                                        frameList.get(j)[i] = (byte) ((lng >> ((7 - i) * 8)) & 0xFF);
                                    } // for

                                    sendFrame(frameList.get(j));

                                    frameList.remove(j);
                                }
                            }

                            frameList.remove(frameAux);
                            isfirst = false;
                        }
                        listIncrementor++;
                    }

                }

            } // try

            /***************************************************************
             *	When we reach the end of the input stream, an exception is
             * 	thrown which is shown below. At this point, you should
             * 	finish up any processing, close your ports and exit.
             ***************************************************************/

            catch (EndOfStreamException e)
            {
                for(byte[] frameAux : frameList)
                {
                    long idNegative, lng; // Long in conversion of double to bytes

                    idNegative = Double.doubleToLongBits(idN);

                    for (i = firstPressureIdByteToRead; i < lastPressureIdByteToRead; i++)
                    {
                        frameAux[i] = (byte) ((idNegative >> ((7 - i) * 8)) & 0xFF);
                    } // for

                    lng = Double.doubleToLongBits(nextValid); // Convert of mesurement to bytes to send to next filter

                    for (i = firstPressureByteToRead; i < lastPressureByteToRead; i++)
                    {
                        frameAux[i] = (byte) ((lng >> ((7 - i) * 8)) & 0xFF);
                    } // for

                    sendFrame(frameAux);
                }
                ClosePorts();
                break;

            } // catch
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) try {
                    bw.close();
                } catch (IOException ioe2) {
                    // ignore it  or write notice
                }
            }
        } // while

    } // run



    /***
     * Function to transform a measurment in bytes and
     * write to outputport
     * @param measure
     */
    protected void writeToNextFilter(Double measure)
    {

        byte[] outputMeasure = new byte[8]; // Byte variable to send data to stream

        long lng; // Long in conversion of double to bytes

        lng = Double.doubleToLongBits(measure);                        // Convert of mesurement to bytes to send to next filter

        for (int i = 0; i < 8; i++) {
            outputMeasure[i] = (byte) ((lng >> ((7 - i) * 8)) & 0xFF);
            WriteFilterOutputPort(outputMeasure[i]);
        } // for
    }

    protected long readMeasurement(int first, int last, byte[] frame)
    {
        long measurement = 0;

        for(int k = first; k < last; k++){

            measurement = measurement | (frame[k] & 0xFF);

            if (k != last-1)					            // If this is not the last byte, then slide the
            {												// previously appended byte to the left by one byte
                measurement = measurement << 8;				// to make room for the next byte we append to the
            } //if
        }

        return measurement;
    }

    protected BufferedWriter writeWildPointsToFile(long time, double pressure, BufferedWriter bufferedWriter) throws IOException
    {
        Calendar TimeStamp = Calendar.getInstance();

        SimpleDateFormat TimeStampFormat = new SimpleDateFormat("yyyy MM dd::hh:mm:ss:SSS");

        TimeStamp.setTimeInMillis(time);

        bufferedWriter.write(TimeStampFormat.format(TimeStamp.getTime()) + "  " + pressure);

        bufferedWriter.newLine();

        bufferedWriter.flush();

        return bufferedWriter;
    }

    protected void sendFrame(byte[] frame)
    {

        for(int i = 0; i<60; i++){
            WriteFilterOutputPort(frame[i]);
        }
    }
} // FilterTemplate