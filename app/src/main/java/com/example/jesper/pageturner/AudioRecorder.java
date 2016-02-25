package com.example.jesper.pageturner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;


public class AudioRecorder {
    private int samplerate;
    private AudioRecord audioRecord = null;
    AudioRecorder(int sampleRate){
        setSampleRate(sampleRate);
        audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.DEFAULT,
                getSamplerate(),
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                AudioRecord.getMinBufferSize(
                        getSamplerate(),
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT
                )
        );
    }

    public void startRecording(){
        audioRecord.startRecording();
    }

    public void stopRecording(){
        audioRecord.stop();
    }

    private Sample convertToDigital(){
        Sample sample = new Sample();
        return new Sample();
    }

    private int getSamplerate() {
        return samplerate;
    }

    private void setSampleRate(int samplerate){
        this.samplerate = samplerate;
    }

}
