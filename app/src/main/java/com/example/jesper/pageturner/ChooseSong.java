package com.example.jesper.pageturner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ChooseSong extends Activity {
    //List of song names
    private ListView listSongNames;
    private SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);
        loadSongs();
        listSongNames = (ListView) findViewById(R.id.songList);
        adapter = new SongAdapter(this, R.layout.row_layout);
        listSongNames.setAdapter(adapter);

        for(Song songItem : new Song().getList()){
            adapter.add(songItem);
        }

        listSongNames.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Intent intent = new Intent(ChooseSong.this, NotePage.class);
                        startActivity(intent);
                    }
                }
        );

    }
    private void loadSongs(){
        String[] artist_names = getResources().getStringArray(R.array.artists);
        String[] song_titles = getResources().getStringArray(R.array.song_titles);
        int i = 0;
        for (String titles: song_titles) {
            Song song = new Song(R.drawable.notes1, titles, artist_names[i]);
            i++;
        }
    }

}
