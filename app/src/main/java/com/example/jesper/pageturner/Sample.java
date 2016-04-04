package com.example.jesper.pageturner;

//Puts a byte in an Array list.

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Sample {

    private static ArrayList<Short> shortQueue = new ArrayList<>();
    private static int numberOfSecondsSaved = 1;
    private static final int MAX_QUEUE_SIZE = 20000;

    public Sample(short b) { //Sample Constructor. Creates one sample
        if(shortQueue.size() < MAX_QUEUE_SIZE) {
            addShortToQueue(b);
        }
        if(shortQueue.size() > EPCP.getN()){
            EPCP epcp = new EPCP();
            epcp.generateToneOfSignal();
        }
    }

    public Sample(){}

    private void addShortToQueue(short b){ //Add more bytes to the List
        shortQueue.add(b);
        //System.out.println("added to queue" + b + " queue size = " + getSizeOfQueue());
    }
    public void popQueue(int[] result) {
        if (shortQueue.size() < 1 || shortQueue == null || shortQueue.get(0) == null || result == null) {
            return;
        }
        //System.out.println("Size : " + shortQueue.size());
        result[0] = shortQueue.get(0);
        try{
            removeFirstElement();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return;
    }

    private synchronized void removeFirstElement() throws InterruptedException{
        shortQueue.remove(0);
    }

    public int getSizeOfQueue(){
        return this.shortQueue.size();
    }

    public static void resetQueue(){
        shortQueue.clear();
    }
}
