package com.example.jesper.pageturner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class ChooseSong extends AppCompatActivity {
    //List of song names
    ListView listSongNames;
    String[] song_titles;
    String[] artist_names;
    SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);


        listSongNames = (ListView) findViewById(R.id.songList);
        artist_names = getResources().getStringArray(R.array.Artists);
        song_titles = getResources().getStringArray(R.array.song_titles);
        adapter = new SongAdapter(getApplicationContext(), R.layout.row_layout);
        listSongNames.setAdapter(adapter);
        int i = 0;
        for (String titles: song_titles) {
            Song song = new Song(R.drawable.notes1, titles, artist_names[i]);
            adapter.add(song);
            i++;
        }

    }

}
