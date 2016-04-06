package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP2 {/*

    private static final int NUMBER_OF_MILLISECONDS = 1;
    private static double tone = 0;

    //1024, 2048, 4096, 8192, 16384, 32768
    private static final int N = 4096 * NUMBER_OF_MILLISECONDS;
    private Complex[] data;
    private static final int MAX_FREQUENCY = 660;
    private static final int MIN_FREQUENCY = 60;
    private static final int NUMBER_OF_PEAKS_ESTIMATED = 10;
    private static int[] testing_frequencies = new int[MAX_FREQUENCY-MIN_FREQUENCY]; //w0

    public EPCP2(Complex[] data){
        this.setData(data);
        this.initializeVariables();
    }

    private void initializeVariables(){
        int iterator = 0;
        for(int i = MIN_FREQUENCY; i < MAX_FREQUENCY +1; i++){
            testing_frequencies[iterator] = i;
            iterator++;
        }
    }

    public Complex[] getData() { return data; }
    private void setData(Complex[] data) { this.data = data; }

    public static double getTone() { return tone; }
    public static void setTone(double tone) { EPCP2.tone = tone; }

    public static int getN(){ return N; }

    public void generateToneOfSignal(){
        //Complex.show(data,"original data = ");
        Complex[] fft = Complex.fft(this.getData());
        //Complex.show(fft,"fft = ");
        //ArrayList<Double> powerSpectrum = this.getPowerSpectrum(fft);
        ArrayList<Double> harmonicSummationValues = new ArrayList<>(); //Array where results of summations are stored
        for(int i = 0; i < testing_frequencies.length; i++) {
            double w0 =2 * Math.PI * testing_frequencies[i] / AudioRecorder.getSampleFrequency();
            for(int j = 1; j < NUMBER_OF_PEAKS_ESTIMATED +1; j++) {
                double w = w0 * j;
                int indexOfFFT = (int) Math.floor(w/2/Math.PI*N)+1;
                fft[indexOfFFT].abs();
            }

            harmonicSummationValues.add(getPitchEstimation(powerSpectrum, i)); //Store results of pitch estimation of 220 hertz (A)
        }

        System.out.println(" pitch estimation array " + powerSpectrum);
        int argMax = getPositionOfMax(powerSpectrum);
        System.out.println(" max arg " + argMax + " arraysize " + powerSpectrum.size());
        //setTone(getW(pitchEsimation.get(argMax)));
        setTone(getFreqency(argMax));
        System.out.println(" tone = " + EPCP2.getTone());
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

    private ArrayList<Double> getPowerSpectrum(Complex[] complexes){
        ArrayList<Double> result = new ArrayList<>(complexes.length);
        for(int i = 0; i < complexes.length; i++) {
            result.add((complexes[i].abs() * complexes[i].abs())/N);
        }
        return result;
    }

    private double getFreqency(int k){
        int samplingFreq =44100;
        return (k / N) * samplingFreq;
    }
    private double getW(double f){
        double samplingFreq =44100;
        return ((2*Math.PI)*(f/samplingFreq));
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
    }*/
}
