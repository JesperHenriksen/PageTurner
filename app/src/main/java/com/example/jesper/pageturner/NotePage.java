package com.example.jesper.pageturner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class NotePage extends AppCompatActivity {
    private Bitmap x;
    private int i;
    private AudioRecorder recorder = null;
    private boolean isPlaying = false;
    private boolean isFirstRun = true;
    private Thread moveImageThread = null;
    private Thread forwardPedalThread = null;
    private Thread backwardPedalThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        Button button = (Button) findViewById(R.id.buttonUpdate);
        button.setText("Start recording");
        Song.loadSong();
        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        x = createSubsetOfImage(i, 0);
        ImageView img = (ImageView) findViewById(R.id.longsongImage);
        img.setImageBitmap(x);
        i = 100;
        Button updateButton = (Button) findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(new btnClick());

        Button one = (Button) findViewById(R.id.one);
        one.setOnClickListener(new btnClick());

        Button two = (Button) findViewById(R.id.two);
        two.setOnClickListener(new btnClick());

        Button three = (Button) findViewById(R.id.three);
        three.setOnClickListener(new btnClick());

        Button four = (Button) findViewById(R.id.four);
        four.setOnClickListener(new btnClick());

        Button five = (Button) findViewById(R.id.five);
        five.setOnClickListener(new btnClick());

        Button six = (Button) findViewById(R.id.six);
        six.setOnClickListener(new btnClick());

        Button seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(new btnClick());

        Button eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(new btnClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Another activity is taking focus (this activity is about to be "paused").
        if (moveImageThread != null) {
            moveImageThread.notify();
        }
        /*if(backwardPedalThread != null){
            backwardPedalThread.notify();
        }
        if(forwardPedalThread != null) {
            forwardPedalThread.notify();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Another activity is taking focus (this activity is about to be "paused").
        moveImageThread = null;
        //backwardPedalThread = null;
        //forwardPedalThread = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        if (moveImageThread != null) {
            moveImageThread = null;
        }
        /*if(backwardPedalThread != null){
            backwardPedalThread.wait();
        }
        if(forwardPedalThread != null) {
            forwardPedalThread.wait();
        }*/
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

    class btnClick implements View.OnClickListener {
        private ImageView img;
        private int getMovementValue (int i) {
            Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
            return src.getWidth() * i/8;
        }

        private void listenForTone(){

            while(isFirstRun) {
                //System.out.println("Listening for tone " + EPCP.getFrequency());
                if(EPCP.getFrequency() > 80 && EPCP.getFrequency() < 660){
                    System.out.println("Listening for tone complete " + EPCP.getFrequency());
                    isFirstRun = false;
                    return;
                }
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonUpdate:
                    //this.listenForTone();
                    System.out.println("started: is playing = " + isPlaying);
                    if(isPlaying)
                        isPlaying = false;
                    else
                        isPlaying = true;
                    System.out.println("before thread: is playing = " + isPlaying);
                    if (isPlaying) {
                        Button button = (Button) findViewById(v.getId());
                        button.setText("Stop recording");
                        recorder = new AudioRecorder();
                        recorder.startRecording();
                        moveImageThread = new Thread(new Runnable() {
                            public void run() {
                                while (isPlaying) {
                                    try {
                                        moveImageThread.sleep(250);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if(Song.getCurrentIndex() % 3 == 0) {
                                        x = createSubsetOfImage(Song.getCurrentIndex()*100, 0);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ImageView img = (ImageView) findViewById(R.id.longsongImage);
                                                img.setImageBitmap(x);
                                            }
                                        });
                                    }
                                }

                            }
                        }, "Moves image over time");
                        moveImageThread.start();
                    }
                    else if(!isPlaying){
                        System.out.println("stopped thread: isPlaying = " + isPlaying);
                        Button button = (Button) findViewById(v.getId());
                        button.setText("Start recording");
                        recorder.stopRecording();
                        moveImageThread = null;
                    }
                    System.out.println("After thread: is playing = " + isPlaying);
                    break;
                case R.id.one:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(0, 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.two:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(1), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.three:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(2), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.four:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(3), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.five:
                    ImageView img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(4), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.six:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(5), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.seven:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(6), 0);
                    img.setImageBitmap(x);
                    break;
                case R.id.eight:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(7), 0);
                    img.setImageBitmap(x);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_menu_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /*public void backChooseSong(View view)
    {
        Intent intent = new Intent(NotePage.this, ChooseSong.class);
        startActivity(intent);
    }*/

    public Bitmap createSubsetOfImage(int x, int y){
        int widthOfSubset = 1200;
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
        if(x + widthOfSubset > src.getWidth()){
            return Bitmap.createBitmap(src, src.getWidth() - widthOfSubset - 1 , y, widthOfSubset, src.getHeight());
        }
        return Bitmap.createBitmap(src, x, y, widthOfSubset, src.getHeight());
    }
}
