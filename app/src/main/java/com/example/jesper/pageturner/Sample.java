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
    //private static int numberOfSecondsSaved = 1;
    private static final int MAX_QUEUE_SIZE = 20000;

    public Sample(short b) { //Sample Constructor. Creates one sample
        if(shortQueue.size() < MAX_QUEUE_SIZE  && b > 20 && b < 20000) {
            addShortToQueue(b);
        }
        if(shortQueue.size() > EPCP.getN()){
            EPCP epcp = new EPCP(loadSamples());
            epcp.generateToneOfSignal();
        }
    }

    private Complex[] loadSamples(){
        Complex[] dataCollection = new Complex[EPCP.getN()];
        Complex iterator;
        double meanValue = 0.0;
        for(int i = 0; i < EPCP.getN(); i++){
            meanValue += (double) shortQueue.get(i);
        }
        meanValue = meanValue/EPCP.getN();
        for(int i = 0; i < EPCP.getN(); i++){
            iterator = new Complex(this.popQueue() - meanValue,0);
            dataCollection[i] = iterator;
        }
        return dataCollection;
    }

    private void addShortToQueue(short b) { //Add more bytes to the List
        shortQueue.add(b);
        //System.out.println("added to queue" + b + " queue size = " + getSizeOfQueue());
    }

    public int popQueue(){
        int result = 0;
        if (shortQueue.size() < 1 || shortQueue == null || shortQueue.get(0) == null) {
            return 0;
        }
        //System.out.println("Size : " + shortQueue.size());
        result = shortQueue.get(0);
        try{
            removeFirstElement();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return result;
    }

    private synchronized void removeFirstElement() throws InterruptedException { shortQueue.remove(0);}

    public static void resetQueue(){ shortQueue.clear();}
}
