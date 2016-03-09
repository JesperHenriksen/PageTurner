package com.example.jesper.pageturner;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;


public class AudioRecordToFile {
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String OUTPUT_FILE_DIRECTORY = Environment.getExternalStorageDirectory() + "audioRecorder.mpeg4";

    public void startRecording() throws IllegalStateException, IOException{

        File outputFile = new File(OUTPUT_FILE_DIRECTORY);
        if (outputFile.exists())
            outputFile.delete();

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE_DIRECTORY);
        recorder.prepare();
        recorder.start();
    }

    public void stopRecording(){
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    public void playRecording()throws Exception{
        player = new MediaPlayer();
        player.setDataSource(OUTPUT_FILE_DIRECTORY);
        player.prepare();
        player.start();
    }
}
