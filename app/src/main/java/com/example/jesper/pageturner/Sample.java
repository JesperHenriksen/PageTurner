package com.example.jesper.pageturner;

//Puts a byte in an Array list.

import java.util.ArrayList;

public class Sample {

    public double digitalSample;
    ArrayList<Byte> byteArray = new ArrayList<Byte>();

    Sample(Byte b) { //Sample Constructor. Creates one sample with one byte
        byteArray.add(b);
    }

    public void addByteToList(Byte b){ //Add more bytes to the List
        byteArray.add(b);
    }

    public double getDigital() {
        return digitalSample;
    }

    public void setDigital(double digital) {
        this.digitalSample = digital;
    }
}
