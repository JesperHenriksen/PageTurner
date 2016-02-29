package com.example.jesper.pageturner;

import android.app.Activity;
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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AudioRecorder audioRecorderClass = null;
    Button startButton = null;
    Button stopButton = null;
    Button newButton = null;
    TextView console = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        newButton = (Button) findViewById(R.id.recordingButton);
        console = (TextView) findViewById(R.id.console);

        audioRecorderClass = new AudioRecorder();
        startButton.setOnClickListener(new btnClick());
        stopButton.setOnClickListener(new btnClick());
        newButton.setOnClickListener(new btnClick());

    }


    class btnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    audioRecorderClass.startRecording();
                    break;
                case R.id.stopButton:
                    audioRecorderClass.stopRecording();
                    break;
                default:
                    break;
            }
            console.setText("format " + AudioFormat.CHANNEL_IN_MONO + "\n" +
                            " encoding " + AudioFormat.ENCODING_PCM_8BIT + "\n" +
                            " Sample rate: " + audioRecorderClass.getSampleRate() + "\n" +
                            " Min buffer size " + audioRecorderClass.getMinBufferSize() + "\n" +
                            " Recorder state " + audioRecorderClass.getRecorderState()
            );
        }
    }

}
