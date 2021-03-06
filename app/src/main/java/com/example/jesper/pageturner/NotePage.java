package com.example.jesper.pageturner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

public class NotePage extends AppCompatActivity {
    private Bitmap x;
    private int i;
    private AudioRecorder recorder = null;
    private boolean isPlaying = false;
    private boolean isFirstRun = true;
    private Thread moveImageThread = null;
    private Thread pedalThread = null;
    private ImageView forwardButton;
    private ImageView backwardButton;
    private int arrowPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        Button button = (Button) findViewById(R.id.buttonUpdate);
        button.setText("Start");
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

        backwardButton = (ImageView) findViewById(R.id.pushbackward);
        forwardButton = (ImageView) findViewById(R.id.pushforward);
        forwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        arrowPressed = 1;
                        forwardButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.pusheddowntwo, null));
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        arrowPressed = 0;
                        forwardButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.notpushedtwo, null));
                        return true;
                }
                return false;
            }
        });
        backwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        arrowPressed = 2;
                        backwardButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.pusheddownone, null));
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        arrowPressed = 0;
                        backwardButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.notpushedone, null));
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Another activity is taking focus (this activity is about to be "paused").
        if (moveImageThread != null) {
            moveImageThread.notify();
        }
        /*if(pedalThread != null){
            pedalThread.notify();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Another activity is taking focus (this activity is about to be "paused").
        moveImageThread = null;
        //pedalThread = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        if (moveImageThread != null) {
            moveImageThread = null;
        }/*
        if(pedalThread != null) {
            pedalThread.wait();
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
                Song.resetSong();
                moveImageThread = null;
                if(recorder != null) {
                    recorder.stopRecording();
                }
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
        private boolean isPedalPressed = false;
        private ImageView img;
        private int getMovementValue (int i) {
            Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
            return src.getWidth() * i/8;
        }

        private void pedalsPressed(){
            pedalThread = new Thread(new Runnable() {
                public void run() {
                    int currentData = Bluetooth.getData();
                    if(currentData == 4) {
                        //System.out.println("pedals " + currentData);
                        while (currentData == 4 && isPedalPressed) {//scroll backward
                            //System.out.println("pedals pressed backward");
                            Song.previousChord();
                            x = createSubsetOfImage(Song.getCurrentIndex(), 0);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageView img = (ImageView) findViewById(R.id.longsongImage);
                                    img.setImageBitmap(x);
                                }
                            });
                            currentData = Bluetooth.getData();
                        }
                        //System.out.println("out pedals " + currentData);
                    }
                    else {
                        //System.out.println("pedals " + currentData);
                        while (currentData == 5 && isPedalPressed) { //scroll forward
                            //System.out.println("pedals pressed forward");
                            Song.nextChord();
                            x = createSubsetOfImage(Song.getCurrentIndex(), 0);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageView img = (ImageView) findViewById(R.id.longsongImage);
                                    img.setImageBitmap(x);
                                }
                            });
                            currentData = Bluetooth.getData();
                        }
                        //System.out.println("out pedals " + currentData);
                    }
                    pedalsReleased();
                }
            }, "Moves image when pedals are pressed");
            pedalThread.start();
        }

        private void pedalsReleased(){
            System.out.println("Pedals released");
            isPedalPressed = false;
            pedalThread = null;
            imageMovement();
        }

        private void imageMovement(){
            moveImageThread = new Thread(new Runnable() {
                public void run() {
                    while (isPlaying && !isPedalPressed) {
                        //System.out.println("pedals not pressed");
                        try {
                            moveImageThread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(arrowPressed == 1){
                            Song.nextChord();
                        }
                        else if(arrowPressed == 2) {
                            Song.previousChord();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(Song.getCurrentIndex() % 3 == 0) {
                                    x = createSubsetOfImage(Song.getCurrentIndex(), 0);
                                    ImageView img = (ImageView) findViewById(R.id.longsongImage);
                                    img.setImageBitmap(x);
                                }
                                LinearLayout skipbuttons = (LinearLayout) findViewById(R.id.skipbuttons);
                                View marker = (View) findViewById(R.id.marker);
                                marker.setTranslationX(Song.getCurrentIndex() *
                                        (skipbuttons.getWidth() / Song.getNumberOfTotalChords()));

                                /*if(isNextNote()){
                                    ImageView chordView = (ImageView) findViewById(R.id.gKey);
                                    chordView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.notelightup, null));
                                }
                                else {
                                    ImageView chordView = (ImageView) findViewById(R.id.gKey);
                                    chordView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.notefrontpage, null));
                                }*/
                            }
                        });
                        if(Bluetooth.getData() > 3){
                            System.out.println("pedals Pressed");
                            isPedalPressed = true;
                            pedalsPressed();
                            moveImageThread = null;
                        }
                    }

                }
            }, "Moves image over time");
            moveImageThread.start();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonUpdate:
                    //this.listenForTone();
                    Song.loadSong();
                    System.out.println("started: is playing = " + isPlaying);
                    if(isPlaying) {
                        isPlaying = false;
                        View marker = findViewById(R.id.marker);
                        marker.setVisibility(View.INVISIBLE);
                    }
                    else {
                        isPlaying = true;
                        View marker = findViewById(R.id.marker);
                        marker.setVisibility(View.VISIBLE);
                    }
                    //System.out.println("before thread: is playing = " + isPlaying);
                    if (isPlaying) {
                        Button button = (Button) findViewById(v.getId());
                        button.setText("Stop");
                        recorder = new AudioRecorder();
                        recorder.startRecording();
                        imageMovement();
                    }
                    else if(!isPlaying){
                        System.out.println("stopped thread: isPlaying = " + isPlaying);
                        Button button = (Button) findViewById(v.getId());
                        button.setText("Start");
                        recorder.stopRecording();
                        moveImageThread = null;
                    }
                    //System.out.println("After thread: is playing = " + isPlaying);
                    break;
                case R.id.one:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(0);
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(0);
                    break;
                case R.id.two:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(1));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 2 / 8);
                    break;
                case R.id.three:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(2));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 3 / 8);
                    break;
                case R.id.four:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(3));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 4 / 8);
                    break;
                case R.id.five:
                    ImageView img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(4));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 5 / 8);
                    break;
                case R.id.six:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(5));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 6 / 8);
                    break;
                case R.id.seven:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(6));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 7 / 8);
                    break;
                case R.id.eight:
                    img = (ImageView) findViewById(R.id.longsongImage);
                    x = createSubsetOfImage(this.getMovementValue(7));
                    img.setImageBitmap(x);
                    Song.setCurrentIndex(Song.getNumberOfTotalChords() * 8 / 8);
                    break;
                case R.id.pushbackward:
                    break;
                case R.id.pushforward:
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
        int widthOfSubset = 1800;
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
        return Bitmap.createBitmap(src, (x) * ((src.getWidth()-widthOfSubset)/(Song.getNumberOfTotalChords()-1)), y, widthOfSubset, src.getHeight());
    }

    public Bitmap createSubsetOfImage(int x){
        int widthOfSubset = 1800;
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.longsong);
        if(x + widthOfSubset > src.getWidth()){
            return Bitmap.createBitmap(src, src.getWidth() - widthOfSubset - 1 , 0, widthOfSubset, src.getHeight());
        }
        return Bitmap.createBitmap(src, x, 0, widthOfSubset, src.getHeight());
    }

/*    private void changeColorOfLinearLayoutChild(int index, LinearLayout view){
        TextView button = (TextView) view.getChildAt(index);
        button.setTextColor(0xFF00FF00);
        button.setTextSize(20);
        TextView buttonNext = (TextView) view.getChildAt(index + 1);
        buttonNext.setTextSize(40);
    }
    /*public void moveMarker(){
        int x = Song.getCurrentIndex() *  (skipbuttons.getWidth() / Song.getNumberOfTotalChords()) + skipbuttons.getLeft();
        int height = marker.getHeight();
        int width = marker.getWidth();
    }*/

}
