package com.example.jesper.pageturner;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;


public class AudioRecorder {
    private int samplerate;
    private MediaRecorder mediaRecorder = new MediaRecorder();
    AudioRecorder(int sampleRate){
        setSampleRate(sampleRate);
    }

    public void startRecording(){
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setOutputFile("mfile");
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.start();
        try{
            mediaRecorder.prepare();
        } catch (IOException e){
            Log.e("media recorder", "prepare() failed!");
        }
        mediaRecorder.start();
    }

    public void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.release();
    }

    private Sample convertToDigital(){
        Sample sample = new Sample();
        return new Sample();
    }

    public int getSamplerate() {
        return samplerate;
    }

    private void setSampleRate(int samplerate){
        this.samplerate = samplerate;
    }

}
