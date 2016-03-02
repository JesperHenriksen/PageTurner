package com.example.jesper.pageturner;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    ListView list;
    int [] note_image_resource = {R.drawable.notes1, R.drawable.notes1, R.drawable.notes1};
    String[] song_titles;
    String[] artist_names;
    SongAdapter adapter;

    AudioRecorder audioRecorderClass = null;
    Button startButton = null;
    Button stopButton = null;
    Button newButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        newButton = (Button) findViewById(R.id.recordingButton);

        startButton.setOnClickListener(new btnClick());
        stopButton.setOnClickListener(new btnClick());
        newButton.setOnClickListener(new btnClick());*/
        audioRecorderClass = new AudioRecorder();

        list = (ListView)findViewById(R.id.songList);
        artist_names = getResources().getStringArray(R.array.Artists);
        song_titles = getResources().getStringArray(R.array.song_titles);
        int i = 0;
        adapter = new SongAdapter(getApplicationContext(), R.layout.row_layout);
        list.setAdapter(adapter);

        for (String titles: song_titles) {
            Song song = new Song(note_image_resource[i], titles, artist_names[i]);
            adapter.add(song);
            i++;
        }


    }


    class btnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /*switch (v.getId()) {
               case R.id.startButton:
                    audioRecorderClass.startRecording();
                    break;
                case R.id.stopButton:
                    audioRecorderClass.stopRecording();
                    break;
                case R.id.recordingButton:
                    break;
                default:
                    break;
            }*/
            //updateText();


        }
    }

    public void updateText(){
        TextView console = (TextView) findViewById(R.id.console);
        console.setText(/*"format " + AudioFormat.CHANNEL_IN_MONO + "\n" +
                        " encoding " + AudioFormat.ENCODING_PCM_8BIT + "\n" +
                        " Sample rate: " + audioRecorderClass.getSampleRate() + "\n" +
                        " Min buffer size " + audioRecorderClass.getMinBufferSize() + "\n" +
                        " Recorder state " + audioRecorderClass.getRecorderState() + "\n" +*/
                        " recording " + new Sample().popQueue()
        );
    }
}
