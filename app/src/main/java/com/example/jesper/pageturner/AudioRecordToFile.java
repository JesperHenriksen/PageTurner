package com.example.jesper.pageturner;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;


public class AudioRecordToFile {
    private MediaRecorder recorder;
    private MediaPlayer player;
    private static final String OUTPUT_FILE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording2.3gp";

    public void startRecording() throws Exception{

        File save = new File(OUTPUT_FILE_PATH, "recording2.3pg");

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE_PATH);

        recorder.prepare();
        recorder.start();
    }

    public void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder  = null;

    }

    public void playRecording() {
        player = new MediaPlayer();

        try {
            player.setDataSource(OUTPUT_FILE_PATH);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player.prepare();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        player.start();
    }
}
