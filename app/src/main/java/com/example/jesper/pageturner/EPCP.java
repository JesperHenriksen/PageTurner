package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private double meanSampleValue = 0;
    private static final int MAX_SAMPLES_PER_TONE = 3800;

    private double getMeanSampleValue() {
        return meanSampleValue;
    }

    private void setMeanSampleValue(double meanSampleValue) {
        this.meanSampleValue = meanSampleValue;
    }

    private void updateMeanValue(){
        setMeanSampleValue(0);
        for(int i = 0; i <MAX_SAMPLES_PER_TONE; i++){
            setMeanSampleValue(getMeanSampleValue() + getSample());
        }
        setMeanSampleValue(getMeanSampleValue()/MAX_SAMPLES_PER_TONE);
    }

    private short getSample(){
        return new Sample().popQueue();
    }
}
