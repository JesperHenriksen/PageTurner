package com.example.jesper.pageturner;

//Puts a byte in an Array list.

import java.util.ArrayList;

public class Sample {

    private static ArrayList<Short> sampleQueue = new ArrayList<>();
    private static int numberOfSecondsSaved = 10;
    private static final int MAX_QUEUE_SIZE = 44100 * numberOfSecondsSaved;

    Sample(){}

    Sample(short b) { //Sample Constructor. Creates one sample with one byte
        addShortToQueue(b);
        if(sampleQueue.size() > MAX_QUEUE_SIZE)
            this.popQueue();
    }
    private short getFirstElement(){
        return this.getQueue().get(0);
    }
    private ArrayList<Short> getQueue(){
        return sampleQueue;
    }
    private void removeFirstElement(){
        this.getQueue().remove(0);
    }
    private void addShortToQueue(short b){ //Add more bytes to the List
        sampleQueue.add(b);
    }
    public short popQueue(){
        if(sampleQueue.isEmpty())
            return 0;
        short result = getFirstElement();
        this.removeFirstElement();
        return result;
    }
    public void resetQueue(){
        sampleQueue.clear();
    }
}
