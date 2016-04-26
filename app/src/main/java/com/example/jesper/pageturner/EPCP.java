package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private Complex[] data;
    private static final int NUMBER_OF_MILLISECONDS = 1;
    //1024, 2048, 4096, 8192, 16384, 32768
    private static final int N = 1024 * NUMBER_OF_MILLISECONDS;
    private static int tone = 0;
    private static final double SAMPLING_MILLISECONDS = N; // 30 milliseconds
    private static final int MAX_FREQUENCY = 660;
    private static final int MIN_FREQUENCY = 60;
    private static final int NUMBER_OF_PEAKS_ESTIMATED = 5;
    private static int[] testingFrequencies = new int[MAX_FREQUENCY - MIN_FREQUENCY]; //w0
    private static int frequency = 0;
    private static ArrayList<Integer> frequencyMedian = new ArrayList<>();

    public EPCP(Complex[] data){
        this.setData(data);
        this.initializeVariables();
    }

    private void initializeVariables(){
        int iterator = 0;
        for(int i = MIN_FREQUENCY; i < MAX_FREQUENCY; i++){
            testingFrequencies[iterator] = i;
            iterator++;
        }
        //System.out.println("length = " + testing_frequencies.length + " testing FQ min = " + testing_frequencies[0] + " Max " + testing_frequencies[600-1]);
    }

    //test signal at 150 hz, 0.03 seconds
    private static Complex[] testSignal(double amplitude) {
        int N = EPCP.N;
        Complex[] a = new Complex[N];
        for (int i = 0; i < N; i++) {
            Complex iterator = new Complex( amplitude * Math.sin(2 * Math.PI * i * 150 / AudioRecorder.getSampleFrequency()),0);
            a[i] = iterator;
        }
        return a;
    }

    public Complex[] getData() { return data; }
    private void setData(Complex[] data) { this.data = data; }

    public static int getTone() { return tone; }
    public static void setTone(int tone) { EPCP.tone = tone; }

    public static int getFrequency() {
        return frequency;
    }

    public static void setFrequency(int frequency) {
        EPCP.frequency = frequency;
    }

    public static int getN(){ return N; }

    public void generateToneOfSignal(){
        double resultSummation = 0;
        //setData(testSignal(1)); // testing at 150 hz for a periodic signal for 0.03 seconds
        //Complex.show(data,"original data = ");
        Complex[] fft = Complex.fft(this.getData());
        //Complex.show(fft,"fft = ");
        double w;
        ArrayList<Double> harmonicSummationValues = new ArrayList<>(); //Array where results of summations are stored
        for(int i = 0; i < testingFrequencies.length; i++) { //testing of all test frequencies, testing_frequencies[i]
            double w0 = 2 * Math.PI * testingFrequencies[i] / AudioRecorder.getSampleFrequency(); //calculate w0 of currect frequency
            for(int j = 1; j < NUMBER_OF_PEAKS_ESTIMATED +1; j++) { // tests current frequency at number of peaks
                w = w0 * j; // calculates different w0 values times a scalar
                //System.out.print("w " + w + " ");
                int indexOfFFT = (int) (Math.floor(w/2/Math.PI*N)); //Converts the frequency to index of fft
                //System.out.print("index " + indexOfFFT);
                resultSummation += Math.pow((fft[indexOfFFT].abs())/SAMPLING_MILLISECONDS, 2); // sun up the different absolute fft values at given index squared ( sum up power spectrums)
                //System.out.print(" fft at index " + fft[indexOfFFT] + " fft abs = " + fft[indexOfFFT].abs());
            }
            //System.out.println("");
            harmonicSummationValues.add(resultSummation); //Store results of pitch estimation of 220 hertz (A)
            resultSummation = 0;
        }

        //System.out.println(" harmonics " + harmonicSummationValues);
        int argMax = getPositionOfMax(harmonicSummationValues);
        //System.out.println(" max arg " + argMax + " arraysize " + " w " + w);
        setFrequency(getFreqencyOfIndex(argMax));
        //System.out.println(" frequency = " + getFrequency());
        setTone(getMedian());
        System.out.println("Tone = " +getTone()+ " expected = " + Song.getCurrentChord().getFrequency());
        if(Chord.isEqual(getFundamentalFrequency(getTone()), Song.getCurrentChord().getFrequency())) {
            Song.nextChord();
        }
    }

    private int getFundamentalFrequency(int tone){
        if(tone == 0){
            return 0;
        }
        int result = tone;
        if (result > 440) {
            result = (tone / 2);
        }

        if(result < 220){
            result = tone * 2;
        }

        return result;
    }

    private int getMedian(){
        frequencyMedian.add(getFrequency());
        if(frequencyMedian.size() > 7){
            frequencyMedian.remove(0);
        }
        ArrayList<Integer> x = new ArrayList<>(frequencyMedian);
        bubbleSort(x);
        if(x.size() > 4)
            return(x.get((x.size()/2 )- 1));
        else
            return 0;
    }

    private void bubbleSort(ArrayList<Integer> x){
        int temp;
        int numberOfSwaps;
        boolean stop = false;
        while(!stop) {
            numberOfSwaps = 0;
            for (int i = 1; i < x.size(); i++) {
                if (x.get(i - 1) > x.get(i)) {
                    temp = x.get(i - 1);
                    x.set(i - 1, x.get(i));
                    x.set(i, temp);
                    numberOfSwaps++;
                }
            }
            if(numberOfSwaps == 0){
                stop = true;
            }
        }
    }

    private int getFreqencyOfIndex(int index){
        return testingFrequencies[index];
    }
    //freqency = index / N * sampleFQ
    private double getPitchEstimation(ArrayList<Double> powerSpectrumSquared, int iterator){
        int i = 0, n = 0;
        double sum = 0;
        while(i < powerSpectrumSquared.size() && n < 10){
            sum += (powerSpectrumSquared.get(i));
            i += iterator;
            n++;
        }
        return sum;
    }

    private Integer getPositionOfMax(ArrayList<Double> complexes){
        int result = 0; double max = 0.0;
        for(int i = 0; i < complexes.size(); i++){
            if(max < complexes.get(i)){
                //System.out.println("max (" +max+") is < than " + complexes.get(i));
                max = complexes.get(i);
                result = i;
            }
        }
        return result;
    }
}
