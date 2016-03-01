package com.example.jesper.pageturner;


import java.util.ArrayList;

public class EPCP {

    private Sample sampleCollection = new Sample();
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
            setMeanSampleValue(getMeanSampleValue() + sampleCollection.popQueue());
        }
        setMeanSampleValue(getMeanSampleValue()/MAX_SAMPLES_PER_TONE);
    }
}
