package com.example.jesper.pageturner;

//Puts a byte in an Array list.

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Sample {

    private static ArrayList<Short> shortQueue = new ArrayList<Short>();
    private static int numberOfSecondsSaved = 2;
    private static final int MAX_QUEUE_SIZE = 44100 * numberOfSecondsSaved;

    Sample(short b) { //Sample Constructor. Creates one sample with one byte
        addShortToQueue(b);
        if(shortQueue.size() > MAX_QUEUE_SIZE)
            shortQueue.remove(0);
    }

    private void addShortToQueue(short b){ //Add more bytes to the List
        shortQueue.add(b);
    }
    public static short popQueue(){
        if(shortQueue.size() <= 0)
            return 0;
        short result = shortQueue.get(0);
        shortQueue.remove(0);
        return result;
    }
    public static void resetQueue(){
        shortQueue.clear();
    }
}
