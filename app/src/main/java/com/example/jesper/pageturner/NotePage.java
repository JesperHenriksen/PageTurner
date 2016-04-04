package com.example.jesper.pageturner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.SpinnerAdapter;

public class NotePage extends AppCompatActivity {
    private Bitmap x;
    private int i;
    private AudioRecorder recorder = null;
    private boolean isRecording = false;
    private Thread toneSamplingThread = null;
    private double test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recorder = new AudioRecorder();

        Button fab = (Button) findViewById(R.id.buttonUpdate);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                x = createSubsetOfImage(500 + i, 0);
                ImageView img = (ImageView) findViewById(R.id.longsongImage);
                img.setImageBitmap(x);
                i += 100;
            }
        });
        final Button recordButton = (Button) findViewById(R.id.buttonTestRecord);
        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (isRecording == false) {
                    try {
                        recorder.startRecording();
                        recordButton.setText("Stop Recording");
                        isRecording = true;
                        toneSamplingThread = new Thread(new Runnable() {
                            public void run() {
                                while(isRecording){
                                    System.out.println("tone value = " + EPCP.getTone());
                                }
                            }
                        }, "Getting Tone of Signal Thread");
                        toneSamplingThread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    recorder.stopRecording();
                    toneSamplingThread = null;
                    isRecording = false;
                    recordButton.setText("Start Recording");
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                Intent back = new Intent(this, ChooseSong.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                return true;
            case R.id.resetSongToolbar:
                Intent resetSong = new Intent(this, NotePage.class);
                resetSong.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(resetSong);
                return true;
            case R.id.showsheetmusicToolbar:
                Intent sheetMusic = new Intent(this, ShowSheetMusic.class);
                sheetMusic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(sheetMusic);
                return true;
           /* case R.id.showProgressToolbar:
                Intent progressBar = new Intent(this, ShowProgressBar.class);
                progressBar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(progressBar);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_menu_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void backChooseSong(View view)
    {
        Intent intent = new Intent(NotePage.this, ChooseSong.class);
        startActivity(intent);
    }

    public Bitmap createSubsetOfImage(int x, int y){
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
        Bitmap result = Bitmap.createBitmap(src, x, y, 1800, src.getHeight());

        return result;
    }


}
