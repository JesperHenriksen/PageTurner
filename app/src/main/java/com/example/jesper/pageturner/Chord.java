package com.example.jesper.pageturner;


public class Chord {
    private double chordNumber;
    private String chordName;

    public Chord(String chordName, double chordNumber) { //Chord constructor
        this.setChordNumber(chordNumber);
        this.setChordName(chordName);
    }

    public double getChordNumber() {
        return chordNumber;
    }

    private void setChordNumber(double chordNumber) {
        this.chordNumber = chordNumber;
    }

    public String getChordName() {
        return chordName;
    }

    public void setChordName(String chordName) {
        this.chordName = chordName;
    }
}
