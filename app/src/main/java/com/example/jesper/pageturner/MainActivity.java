package com.example.jesper.pageturner;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    AudioRecorder audioRecorder = null;
    Button startButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClickListener();
        audioRecorder = new AudioRecorder(44100);
    }

    public void buttonClickListener(){
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //setContentView(R.layout.activity_recorder_page);


                audioRecorder.startRecording();
            }
        });
    }
}
