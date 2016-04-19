package com.example.jesper.pageturner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AudioRecordToFile recorder = null;
    private Button startButton = null;
    private Button stopButton = null;
    private Button playButton = null;

    //int[] note_image_resource = {R.drawable.notes1, R.drawable.notes1, R.drawable.notes1}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.button);
        stopButton = (Button) findViewById(R.id.button2);
        playButton = (Button) findViewById(R.id.button3);
        recorder = new AudioRecordToFile();
        startButton.setOnClickListener(new btnClick());
        stopButton.setOnClickListener(new btnClick());
        playButton.setOnClickListener(new btnClick());
        stopButton.setEnabled(false);
        playButton.setEnabled(false);

        Bluetooth bluetooth = new Bluetooth();
        bluetooth.findBT();
    }


    class btnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    try {
                        recorder.startRecording();
                        startButton.setEnabled(false);
                        stopButton.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.button2:
                    recorder.stopRecording();
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    playButton.setEnabled(true);
                    break;
                case R.id.button3:
                    try {
                        recorder.playRecording();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void startChooseSongActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, ChooseSong.class);
        startActivity(intent);
    }

}
