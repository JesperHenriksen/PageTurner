package com.example.jesper.pageturner;

public class Song {

    private int note_image_resource;
    private String song_title;
    private String artist_name;

    public int getNote_image_resource() {
        return note_image_resource;
    }

    public void setNote_image_resource(int note_image_resource) {
        this.note_image_resource = note_image_resource;
    }

    public String getSong_title() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public Song(int note_image_resource, String song_title, String artist_name) {
        this.setNote_image_resource(note_image_resource);
        this.setSong_title(song_title);
        this.setArtist_name(artist_name);
    }
}
