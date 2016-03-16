package com.example.jesper.pageturner;

import java.util.ArrayList;

public class Song {

    private int noteImageResource;
    private String songTitle;
    private String artistName;
    private static ArrayList<Chord> songChords = new ArrayList<>();

    public Song(int note_image_resource, String songTitle, String artistName) {
        this.setNoteImageResource(note_image_resource);
        this.setSongTitle(songTitle);
        this.setArtistName(artistName);
        songChords.add(this.getChordsToSong(this.getSongTitle()));
        SongCollection songCollection = new SongCollection(this);
    }

    private Chord getChordsToSong(String songTitle){
        Chord chord = new Chord("chord",0);
        return chord;
    }
    public int getNoteImageResource() {
        return noteImageResource;
    }

    private void setNoteImageResource(int noteImageResource) {
        this.noteImageResource = noteImageResource;
    }

    public String getSongTitle() {
        return songTitle;
    }

    private void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    private void setArtistName(String artistName) {
        this.artistName = artistName;
    }


}
