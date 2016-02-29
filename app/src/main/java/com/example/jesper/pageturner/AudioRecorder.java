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
    private static final int BUFFER_ELEMENTS_TO_RECORD = 1024;
    private static final int BYTES_PER_ELEMENT = 2;
    private int samplerate = 0;
    private AudioRecord audioRecord = null;
    private int bufferSize = 0;
    private int buffercount= 0;
    private Thread recordingThread = null;


    AudioRecorder(int sampleRate){
        setSampleRate(sampleRate);
        bufferSize = AudioRecord.getMinBufferSize(
                this.getSampleRate(),
                RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING
        );



    }

    public void startRecording(){
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                this.getSampleRate(),
                RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING,
                BUFFER_ELEMENTS_TO_RECORD * BYTES_PER_ELEMENT
        );
        //audioRecord.startRecording();
    }

    public void stopRecording(){
        audioRecord.stop();
    }

    /*private Sample convertToDigital(){
        Sample sample = new Sample();
        return new Sample();
    }*/

    private int getSampleRate() {
        return samplerate;
    }

    private void setSampleRate(int samplerate){
        this.samplerate = this.samplerate == 0 ? samplerate : this.samplerate;
    }

}
