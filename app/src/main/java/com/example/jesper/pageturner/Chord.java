package com.example.jesper.pageturner;


public class Chord {
    private int chordNumber;
    private String chordName;

    public Chord(String chordName) { //Chord constructor
        this.setChordName(chordName);
        this.setFrequency(chordName);
    }

    public static boolean isEqual(int e, int i){
        int range = 15;
        if(e + range > i && e - range < i)
            return true;
        else
            return false;
    }

    public int getFrequency() {
        return chordNumber;
    }

    private void setFrequency(String chordName) {
        switch(chordName){
            case "A":
                this.chordNumber = 220;
                break;
            case "A#":
                this.chordNumber = 233;
                break;
            case "B":
                this.chordNumber = 247;
                break;
            case "C":
                this.chordNumber = 262;
                break;
            case "C#":
                this.chordNumber = 277;
                break;
            case "D":
                this.chordNumber = 294;
                break;
            case "D#":
                this.chordNumber = 311;
                break;
            case "E":
                this.chordNumber = 330;
                break;
            case "F":
                this.chordNumber = 349;
                break;
            case "F#":
                this.chordNumber = 370;
                break;
            case "G":
                this.chordNumber = 392;
                break;
            case "G#":
                this.chordNumber = 415;
                break;
            default:
                break;
        }
    }

    public String getChordName() {
        return chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }
}
