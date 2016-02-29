package com.example.jesper.pageturner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;


public class AudioRecorder {
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int RECORDER_SAMPLE_RATE = 44100;

    private AudioRecord audioRecord = null;
    private int minBufferSize = 0;
    private Thread recordingThread = null;
    private short[] buffer = null;

    AudioRecorder(){
        minBufferSize = AudioRecord.getMinBufferSize(
                RECORDER_SAMPLE_RATE,
                RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING
        );
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLE_RATE,
                RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING,
                minBufferSize
        );
        buffer = new short[minBufferSize];
    }
    public int getMinBufferSize() {
        return minBufferSize;
    }


    public void startRecording(){
        audioRecord.startRecording();
        audioRecord.read(buffer, 0 , minBufferSize);
    }

    public int getRecorderState(){
        return audioRecord.getRecordingState();
    }

    public void stopRecording(){
        audioRecord.stop();
        //audioRecord.release();
    }

    public int getSampleRate() {
        return RECORDER_SAMPLE_RATE;
    }

    /*private void setSampleRate(int samplerate){
        this.samplerate = this.samplerate == 0 ? samplerate : this.samplerate;
    }*/
   /*private Sample convertToDigital(){
        Sample sample = new Sample();
        return new Sample();
    }*/
        /*AudioRecorder(int sampleRate){
        setSampleRate(sampleRate);

    }*/
    /*private void setMinBufferSize(int minBufferSize) {
        this.minBufferSize = minBufferSize;
    }*/

}
