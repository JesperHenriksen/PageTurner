package com.example.jesper.pageturner;

import java.util.List;
import java.util.Queue;

public class ExistingChords {
    private List<Chord> existingChords = null;

    public void addItemToList(Chord c){
        existingChords.add(c);
    }

    public Chord getItemInList(int i){
        return existingChords.get(i);
    }

}
