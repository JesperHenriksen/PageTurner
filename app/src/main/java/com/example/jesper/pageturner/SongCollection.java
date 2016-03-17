package com.example.jesper.pageturner;

import java.util.ArrayList;


public class SongCollection {
    private static ArrayList<Song> songList = new ArrayList<>();

    SongCollection(Song song){
        this.add(song);
    }

    private void add(Song song) {
        songList.add(song);
    }

    SongCollection(){
    }
    public Song getListObject(int i){
        return songList.get(i);
    }

    public String getArtist(String artist) {
        return artist;
    }

    public String getSongTitle(String songTitle) {
        return songTitle;
    }
}
