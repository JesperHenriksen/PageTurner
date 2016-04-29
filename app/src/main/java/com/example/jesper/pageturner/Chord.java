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
        if(e - range < i && e + range > i)
            return true;
        else
            return false;
    }

    public int getFrequency() {
        return chordNumber;
    }

    private void setFrequency(String chordName) {
        if(chordName.equals("A"))
            this.chordNumber = 220;
        if(chordName.equals("A#"))
            this.chordNumber = 233;
        if(chordName.equals("B"))
            this.chordNumber = 247;
        if(chordName.equals("C"))
            this.chordNumber = 262;
        if(chordName.equals("C#"))
            this.chordNumber = 277;
        if(chordName.equals("D"))
            this.chordNumber = 294;
        if(chordName.equals("D#"))
            this.chordNumber = 311;
        if(chordName.equals("E"))
            this.chordNumber = 330;
        if(chordName.equals("F"))
            this.chordNumber = 349;
        if(chordName.equals("F#"))
            this.chordNumber = 370;
        if(chordName.equals("G"))
            this.chordNumber = 392;
        if(chordName.equals("G#"))
            this.chordNumber = 415;
    }

    public String getChordName() {
        return chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }
}
