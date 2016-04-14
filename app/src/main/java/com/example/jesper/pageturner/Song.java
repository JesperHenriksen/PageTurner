package com.example.jesper.pageturner;

import java.util.ArrayList;

public class Song {

    private int noteImageResource;
    public String songTitle;
    public String artistName;
    private static ArrayList<Song> songCollection = new ArrayList<>();
    private static ArrayList<Chord> listOfChords = new ArrayList<>();


    public Song(int noteImageResource, String songTitle, String artistName) {
        this.setNoteImageResource(noteImageResource);
        this.setSongTitle(songTitle);
        this.setArtistName(artistName);
        songCollection.add(this);
    }
    public Song(){}

    public Song(ArrayList<Chord> listOfChords, String artist, String songName) {
        this.setSongTitle(songName);
        this.setArtistName(artist);
        this.setListOfChords(listOfChords);
        SongCollection.add(this);
    }

    /*public ArrayList<Chord> getListOfChords() {
        return listOfChords;
    }*/
    private void setListOfChords(ArrayList<Chord> listOfChords) {
        Song.listOfChords = listOfChords;
    }

    public int getNoteImageResource() {
        return noteImageResource;
    }

    public void setNoteImageResource(int note_image_resource) {
        this.noteImageResource = note_image_resource;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist(){return artistName;}

    /*public String findArtist(String song)
    {
        //An enhanced for loop
        for(Song songList: songCollection){
            if(songList.getArtist().equals(song)){
                return this.getArtist();
            }
        }
        return null;
    }*/

    public void setArtistName(String artist_name) {
        this.artistName = artist_name;
    }

    public ArrayList<Song> getList() {
        return songCollection;
    }
}