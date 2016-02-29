package com.example.jesper.pageturner;

//Puts a byte in an Array list.

import java.util.ArrayList;
import java.util.Queue;

public class Sample {

    private Queue<Short> shortQueue;

    Sample(short b) { //Sample Constructor. Creates one sample with one byte
        addShortToQueue(b);
    }

    private void addShortToQueue(short b){ //Add more bytes to the List
        shortQueue.add(b);
    }
    public short popQueue(){
        return shortQueue.poll();
    }
}
