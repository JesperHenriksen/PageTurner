package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private static final int NUMBER_OF_MILLISECONDS = 1;
    private static double tone = 0;

    //1024, 2048, 4096, 8192, 16384, 32768
    private static final int N = 1024 * NUMBER_OF_MILLISECONDS;
    private Complex[] data;
    private static final double SAMPLING_MILLISECONDS = AudioRecorder.getSampleFrequency() * 0.03; // 30 milliseconds
    private static final int MAX_FREQUENCY = 660;
    private static final int MIN_FREQUENCY = 60;
    private static final int NUMBER_OF_PEAKS_ESTIMATED = 10;
    private static int[] testing_frequencies = new int[MAX_FREQUENCY - MIN_FREQUENCY]; //w0

    public EPCP(Complex[] data){
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
    public static void setTone(double tone) { EPCP.tone = tone; }

    public static int getN(){ return N; }

    public void generateToneOfSignal(){
        double resultSummation = 0;
        //Complex.show(data,"original data = ");
        Complex[] fft = Complex.fft(this.getData());
        //Complex.show(fft,"fft = ");
        ArrayList<Double> harmonicSummationValues = new ArrayList<>(); //Array where results of summations are stored
        for(int i = 0; i < testing_frequencies.length; i++) { //testing of all test frequencies, testing_frequencies[i]
            double w0 =2 * Math.PI * testing_frequencies[i] / AudioRecorder.getSampleFrequency(); //calculate w0 of currect frequency
            for(int j = 1; j < NUMBER_OF_PEAKS_ESTIMATED +1; j++) { // tests current frequency at number of peaks
                double w = w0 * j; // calculates different w0 values times a scalar
                int indexOfFFT = (int) (Math.floor(w/2/Math.PI*N)+1); //Converts the frequency to index of fft
                resultSummation += Math.pow(fft[indexOfFFT].abs()/SAMPLING_MILLISECONDS, 2); // sun up the different absolute fft values at given index squared ( sum up power spectrums)
            }
            harmonicSummationValues.add(resultSummation); //Store results of pitch estimation of 220 hertz (A)
        }

        //System.out.println(" pitch estimation array " + powerSpectrum);
        //int argMax = getPositionOfMax(powerSpectrum);
        //System.out.println(" max arg " + argMax + " arraysize " + powerSpectrum.size());
        //setTone(getW(pitchEsimation.get(argMax)));
        //setTone(getFreqency(argMax));
        //System.out.println(" tone = " + EPCP.getTone());
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
