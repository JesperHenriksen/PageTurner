package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private static final int NUMBER_OF_MILLISECONDS = 1;
    private static double tone = 0;
    private static final int N = 1024 * NUMBER_OF_MILLISECONDS;
    private Complex[] data;
    private static final int MAX_FREQENCY = 221;

    public EPCP(Complex[] data){
        this.setData(data);
    }

    public Complex[] getData() { return data; }
    private void setData(Complex[] data) { this.data = data; }

    public static double getTone() { return tone; }
    public static void setTone(double tone) { EPCP.tone = tone; }

    public static int getN(){ return N; }

    public void generateToneOfSignal(){
        Complex.show(data,"original data = ");
        Complex[] fft = Complex.fft(this.getData());
        Complex.show(fft,"fft = ");
        ArrayList<Double> powerSpectrum = this.getPowerSpectrum(fft);
        ArrayList<Double> pitchEsimation = new ArrayList<>(); //Array where results of summations are stored
        /*for(int i = 1; i < N; i += 10) {
            pitchEsimation.add(getPitchEstimation(powerSpectrum, i)); //Store results of pitch estimation of 220 hertz (A)
        }*/

        System.out.println(" pitch estimation array " + powerSpectrum);
        int argMax = getPositionOfMax(powerSpectrum);
        System.out.println(" max arg " + argMax + " arraysize " + powerSpectrum.size());
        //setTone(getW(pitchEsimation.get(argMax)));
        setTone(getFreqency(argMax));
        System.out.println(" tone = " + EPCP.getTone());
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
    }
}
