package com.example.jesper.pageturner;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class AudioRecorder {
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int RECORDER_SAMPLE_RATE = 44100;

    private AudioRecord audioRecord = null;
    private int minBufferSize = 0;
    private Thread recordingThread = null;
    private short buffer[] = null;
    public boolean isRecording = false;


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
        isRecording = true;
        audioRecord.startRecording();
        Sample.resetQueue();
        recordingThread = new Thread(new Runnable() {
            public void run() {
                while(isRecording)
                    recording();
            }
        }, "AudioRecorder Thread");
        recordingThread.start();
    }

    private void recording(){
        int zeroCounter = 0;
        audioRecord.read(buffer, 0 , minBufferSize);
        for(int i = 0; i < buffer.length; i++) {
            if (buffer[i] == 0) {
                zeroCounter ++;
                if(zeroCounter > 500)
                    break;
            }
            if(buffer[i] != 0) {
                new Sample(buffer[i]);
                buffer[i] = 0;
            }
        }
    }

    public int getRecorderState(){
        return audioRecord.getRecordingState();
    }

    public void stopRecording(){
        System.out.println("Stopping recorder... ");
        isRecording = false;
        this.audioRecord.stop();
        //audioRecord.release();
        this.recordingThread = null;
    }

    public int getSampleRate() {
        return RECORDER_SAMPLE_RATE;
    }
}
