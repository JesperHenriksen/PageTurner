package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private static final int NUMBER_OF_MILLISECONDS = 1;
    private static double tone = 0;
    private static final int N = 1024 * NUMBER_OF_MILLISECONDS;
    private Complex[] data;

    public EPCP(){
        loadSamples();
    }

    public Complex[] getData() {
        return data;
    }


    public static double getTone() {
        return tone;
    }

    public static void setTone(double tone) {
        EPCP.tone = tone;
    }

    private void setData(Complex[] data) {
        this.data = data;
    }

    public static int getN(){
        return N;
    }

    private void loadSamples(){
        Complex[] dataCollection = new Complex[N];
        Complex iterator;
        int[] result = new int[10];
        for(int i = 0; i < N; i++){
            new Sample().popQueue(result);
            iterator = new Complex(result[0],0);
            dataCollection[i] = iterator;
        }
        setData(dataCollection);
    }


    private Complex[] getFFT(){
        Complex[] fft = Complex.fft(this.getData());
        return fft;
    }

    public void generateToneOfSignal(){
        Complex[] fft = this.getFFT();
        ArrayList<Double> powerSpectrum = this.getPowerSpectrum(fft);
        int argMax = getPositionOfMax(powerSpectrum);
        setTone(getW(argMax));
    }

    private double getHarmonicSpectrum(ArrayList<Double> list) {
        double result = 0;
        for (int i = 0; i < list.size(); i++){
            result += list.get(i);
        }
        return result;
    }

    private ArrayList<Double> getPowerSpectrum(Complex[] complexes){
        ArrayList<Double> result = new ArrayList<>(complexes.length);
        for(int i = 0; i < complexes.length; i++){
            result.add(complexes[i].abs());
        }
        return result;
    }

    private double getFreqency(int w){
        int samplingFreq =44100;
        return (w*samplingFreq)/(2*Math.PI);
    }
    private double getW(int f){
        double samplingFreq =44100;
        return ((2*Math.PI)*(f/samplingFreq));
    }
    private Integer getPositionOfMax(ArrayList<Double> complexes){
        int result = 0; double max = 0.0;
        for(int i = 0; i < complexes.size(); i++){
            if(max < complexes.get(i)){
                max = complexes.get(i);
                result = i;
            }
        }
        return result;
    }


    private void filterZeroFromPowerSpectrum(ArrayList<Double> input){
        int i = 0;
        while (i != input.size()){
            if(input.get(i) == 0){
                input.remove(i);
            }
            else
            {
                i++;
            }
        }
    }

    private int getAutoCorrelation(){
        double highValue = 0;
        double rxx = 0;
        for (int i = 0; i < N; i++) {
            for (int n = 0; n < N; n++) {
                rxx = this.getData()[n].getRe() * this.getData()[n].getRe() - i;
            }
            if (i == 0)
                highValue = rxx;
            if (rxx >= highValue && i != 0)
                return i;
        }
        return 0;
    }
}
