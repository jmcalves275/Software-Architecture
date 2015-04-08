/**
 * ***************************************************************************************************************
 * File:PlumberTemplate.java
 * Course: 17655
 * Project: Assignment 1
 * Copyright: Copyright (c) 2003 Carnegie Mellon University
 * Versions:
 * 1.0 November 2008 - Initial rewrite of original assignment 1 (ajl).
 * <p/>
 * Description:
 * <p/>
 * This class serves as a template for creating a main thread that will instantiate and connect a set of filters.
 * The details of the filter operations is totally self contained within the filters, the "plumber" takes care of
 * starting the filters and connecting each of the filters together. The details of how to connect filters is taken
 * care of by the FilterFramework. In order to use this template the program should rename the class.
 * The template includes the RunFilter() method which is executed when the filter is started. While simple, there
 * are semantics for instantiating, connecting, and starting the filters:
 * <p/>
 * Step 1: Instantiate the filters as shown in the example below. You should create the filters using the
 * templates provided, and you must use the FilterFramework as a base class for all filters. Every pipe and
 * filter network must have a source filter where data originates, and a sink filter where the data flow
 * terminates.
 * <p/>
 * Step 2: Connect the filters. Start with the sink and work backward to the source. Essentially you are connecting
 * the	input of each filter to the up-stream filter's output until you get to the source filter. Filter have
 * a Connect() method which accepts a FilterFramework type. This method connects the calling filter's input
 * to the passed filter's output. Again the example in the comments below illustrates how this is done.
 * <p/>
 * Step 3: Start the filters using the start() method.
 * <p/>
 * Once the filters are started this main thread dies and the pipe and filter network processes data until there
 * is no more data movement from the source. Each filter will shutdown when data is no longer available (provided
 * you followe the read semantics described in the filter templates).
 * on their input ports.
 * <p/>
 * Parameters: 		None
 * <p/>
 * Internal Methods:	None
 * <p/>
 * ****************************************************************************************************************
 */
public class Plumber {
    public static void main(String argv[]) {

        /* Sistema A */
        SourceFilter source = new SourceFilter();
        Splitter splitter = new Splitter();
        Sink sink = new Sink();
        FrameMerger merger = new FrameMerger();

        Filter tempFilter = new Filter(4);
        Filter height = new Filter(2);
        PressureFilter pressure = new PressureFilter();
        SortFilter sort = new SortFilter(0);

        pressure.Connect(source);
        sort.Connect(pressure);


        splitter.Connect(sort);

        tempFilter.Connect(splitter, splitter.getPort());
        height.Connect(splitter, splitter.getPort());

        merger.Connect(height);
        merger.Connect(tempFilter);


        sink.Connect(merger);

        source.start();
        splitter.start();
        sink.start();
        merger.start();
        tempFilter.start();
        height.start();
        pressure.start();
        sort.start();

        /* Sistema B */


        /* Sistema C */
        /*
        SourceFilter source1 = new SourceFilter();
        Sink sink = new Sink();
        SortFilter sort= new SortFilter(0);
        sort.Connect(source1);
        sink.Connect(sort);
        source1.start();
        sort.start();
        sink.start();
*/


    } // main

} // PlumberTemplate