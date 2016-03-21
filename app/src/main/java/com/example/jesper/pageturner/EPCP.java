package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private static final int NUMBER_OF_MILLISECONDS = 1;
    //amount of samples N
    private static final int N = 441 * NUMBER_OF_MILLISECONDS;
    private Complex[] data;

    EPCP(){
        loadSamples();
    }

    public Complex[] getData() {
        return data;
    }

    private void setData(Complex[] data) {
        this.data = data;
    }

    private void loadSamples(){
        Complex[] dataCollection = new Complex[N];
        Complex iterator;
        for(int i = 0; i < N; i++){
            iterator = new Complex(Sample.popQueue(),0);
            dataCollection[i] = iterator;
        }
        setData(dataCollection);
    }


    private Complex[] getFFT(){
        Complex[] data = this.getData();
        Complex[] fft = Complex.fft(data);
        return fft;
    }

    public Chord getToneOfSignal(){
        Chord chord = new Chord("a",0);
        Complex[] fft = this.getFFT();
        ArrayList<Double> powerSpectrum = this.getPowerSpectrum(fft);
        Double harmonicSpectrum = new Double(this.getHarmonicSpectrum(powerSpectrum));
        return  new Chord("a",0);
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
