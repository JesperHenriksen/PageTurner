package com.example.jesper.pageturner;

import java.util.ArrayList;

public class Song {

    private int noteImageResource;
    public String songTitle;
    public String artistName;
    private static ArrayList<Song> songCollection = new ArrayList<>();
    private static ArrayList<Chord> listOfChords = new ArrayList<>();

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        if(currentIndex > listOfChords.size()){
            Song.currentIndex = listOfChords.size() - 1;
        }
        else
            Song.currentIndex = currentIndex;
    }
    public static int getNumberOfTotalChords(){
        return listOfChords.size();
    }

    private static int currentIndex = 0;


    public Song(int noteImageResource, String songTitle, String artistName) {
        this.setNoteImageResource(noteImageResource);
        this.setSongTitle(songTitle);
        this.setArtistName(artistName);
        songCollection.add(this);
    }
    public Song(){}

    public static Chord getCurrentChord(){
        if(listOfChords.size() < currentIndex)
            return listOfChords.get(currentIndex);
        else
            return listOfChords.get(listOfChords.size()-1);
    }

    public static void nextChord(){
        if(listOfChords.size() - 1 > currentIndex) {
            currentIndex++;
        }
    }

    public static void previousChord(){
        if(currentIndex > 0){
            currentIndex--;
        }
    }
    public static void loadSong(){
        //first block
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("E"));
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("B"));
        listOfChords.add(new Chord("A"));
        //second block
        listOfChords.add(new Chord("D")); listOfChords.add(new Chord("E"));
        listOfChords.add(new Chord("F"));
        //third block
        listOfChords.add(new Chord("G")); listOfChords.add(new Chord("B"));
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("F"));
        //fourth block
        listOfChords.add(new Chord("E")); listOfChords.add(new Chord("G"));
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("E"));
        //fifth block
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("E"));
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("G"));
        listOfChords.add(new Chord("E"));
        //sixth block
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("F"));
        listOfChords.add(new Chord("E"));
        //seventh block
        listOfChords.add(new Chord("D")); listOfChords.add(new Chord("C"));
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("G"));
        listOfChords.add(new Chord("A"));
        //eighth block
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("C"));
        listOfChords.add(new Chord("B")); listOfChords.add(new Chord("A"));
        //ninth block
        listOfChords.add(new Chord("E")); listOfChords.add(new Chord("F"));
        listOfChords.add(new Chord("G")); listOfChords.add(new Chord("F"));
        listOfChords.add(new Chord("G"));
        //tenth block
        listOfChords.add(new Chord("B")); listOfChords.add(new Chord("A"));
        listOfChords.add(new Chord("G")); listOfChords.add(new Chord("F"));
        //eleventh block
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("A"));
        listOfChords.add(new Chord("B")); listOfChords.add(new Chord("G"));
        listOfChords.add(new Chord("D"));
        //twelfth block
        listOfChords.add(new Chord("D")); listOfChords.add(new Chord("C#"));
        listOfChords.add(new Chord("B"));
        //thirteenth block
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("A"));
        listOfChords.add(new Chord("A")); listOfChords.add(new Chord("B"));
        listOfChords.add(new Chord("A"));
        //fourteenth block
        listOfChords.add(new Chord("D")); listOfChords.add(new Chord("D"));
        listOfChords.add(new Chord("C"));
        //fifteenth block
        listOfChords.add(new Chord("B")); listOfChords.add(new Chord("C"));
        listOfChords.add(new Chord("D")); listOfChords.add(new Chord("D"));
        listOfChords.add(new Chord("G"));
        //sixteenth block
        listOfChords.add(new Chord("F")); listOfChords.add(new Chord("E"));
       /* for(Chord e: listOfChords){
            System.out.print("chordName " + e.getChordName() + " frequency " + e.getFrequency());
        }
        System.out.println(" ");*/
        //Song song = new Song(listOfChords,"Til vor lille gerning ud", "C.E.F. Weyse");
    }

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

    public static void resetSong() {
        currentIndex = 0;
    }
}