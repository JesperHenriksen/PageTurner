package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private double meanSampleValue = 0;
    private static final int MAX_SAMPLES_PER_TONE = 3800;
    private static final int NUMBER_OF_MILLISECONDS = 1;
    //amount of samples N
    private static final int N = 441 * NUMBER_OF_MILLISECONDS;
    private static ArrayList<Double> fftList = new ArrayList<>();

    private double getMeanSampleValue() {
        return meanSampleValue;
    }

    private void setMeanSampleValue(double meanSampleValue) {
        this.meanSampleValue = meanSampleValue;
    }

    private void updateMeanValue(){
        setMeanSampleValue(0);
        for(int i = 0; i <MAX_SAMPLES_PER_TONE; i++){
            setMeanSampleValue(getMeanSampleValue() + Sample.popQueue());
        }
        setMeanSampleValue(getMeanSampleValue()/MAX_SAMPLES_PER_TONE);
    }


    private static ArrayList<Double> getFFT(){
        Complex[] dataCollection = new Complex[N];
        Complex iterator;
        for(int i = 0; i < N; i++){
            iterator = new Complex(Sample.popQueue(),0);
            dataCollection[i] = iterator;
        }
        Complex[] fft = Complex.fft(dataCollection);
        for (int i = 0; i<N;i++){
            fftList.add(fft[i].getRe());
        }
        return fftList;
    }

    public static Chord getToneOfSignal(){
        Chord chord = new Chord("a",0);
        double bob = EPCP.getFFT().get(0);
        return  new Chord("a",0);
    }
}
