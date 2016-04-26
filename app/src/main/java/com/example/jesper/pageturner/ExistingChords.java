package com.example.jesper.pageturner;

import java.util.ArrayList;
import java.util.List;

public class ExistingChords {
    private static ArrayList<Chord> existingChords = new ArrayList<>();

    /*public ExistingChords(){
        Chord c = new Chord("C", 77);
        addItemToList(c);
        Chord cmol = new Chord("C#", 77);
        addItemToList(cmol);
        Chord d = new Chord("D", 77);
        addItemToList(d);
        Chord dmol = new Chord("D#", 77);
        addItemToList(dmol);
        Chord e = new Chord("E", 77);
        addItemToList(e);
        Chord f = new Chord("F", 77);
        addItemToList(f);
        Chord fmol = new Chord("F#", 77);
        addItemToList(fmol);
        Chord g = new Chord("G", 77);
        addItemToList(g);
        Chord gmol = new Chord("G#", 77);
        addItemToList(gmol);
        Chord a = new Chord("A", 77);
        addItemToList(a);
        Chord amol = new Chord("A#", 77);
        addItemToList(amol);
        Chord b = new Chord("B", 77);
        addItemToList(b);

        Chord cMinor = new Chord("c", 77);
        addItemToList(cMinor);
        Chord cmolMinor = new Chord("c#", 77);
        addItemToList(cmolMinor);
        Chord dMinor = new Chord("d", 77);
        addItemToList(dMinor);
        Chord dmolMinor = new Chord("d#", 77);
        addItemToList(dmolMinor);
        Chord eMinor = new Chord("e", 77);
        addItemToList(eMinor);
        Chord fMinor = new Chord("f", 77);
        addItemToList(fMinor);
        Chord fmolMinor = new Chord("f#", 77);
        addItemToList(fmolMinor);
        Chord gMinor = new Chord("g", 77);
        addItemToList(gMinor);
        Chord gmolMinor = new Chord("g#", 77);
        addItemToList(gmolMinor);
        Chord aMinor = new Chord("a", 77);
        addItemToList(aMinor);
        Chord amolMinor = new Chord("a#", 77);
        addItemToList(amolMinor);
        Chord bMinor = new Chord("b", 77);
        addItemToList(bMinor);

    }*/

    public List<Chord> getExistingChordList(){
        return existingChords;
    }

    private void addItemToList(Chord c){
        existingChords.add(c);
    }

    public Chord getItemInList(int i){
        return existingChords.get(i);
    }

}
