package com.example.jesper.pageturner;

import java.util.ArrayList;

public class Song {

    private int note_image_resource;
    private String song_title;
    private String artist_name;
    private static ArrayList<Song> songCollection = new ArrayList<>();
    private static ArrayList<Chord> listOfChords = new ArrayList<>();


    public Song(int note_image_resource, String song_title, String artist_name) {
        this.setNoteImageResource(note_image_resource);
        this.setSong_title(song_title);
        this.setArtistName(artist_name);
        songCollection.add(this);
    }
    public Song(){}

    public Song(ArrayList<Chord> listOfChords, String artist, String songName) {
        this.setSong_title(songName);
        this.setArtistName(artist);
        this.setListOfChords(listOfChords);
        SongCollection.add(this);
    }

    public ArrayList<Chord> getListOfChords() {
        return listOfChords;
    }
    private void setListOfChords(ArrayList<Chord> listOfChords) {
        Song.listOfChords = listOfChords;
    }

    public int getNoteImageResource() {
        return note_image_resource;
    }

    public void setNoteImageResource(int note_image_resource) {
        this.note_image_resource = note_image_resource;
    }

    public String getSongTitle() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public String getArtist(){return artist_name;}

    public String findArtist(String song)
    {
        //An enhanced for loop
        for(Song songList: songCollection){
            if(songList.getArtist().equals(song)){
                return this.getArtist();
            }
        }
        return null;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }

    public ArrayList<Song> getList() {
        return songCollection;
    }
}