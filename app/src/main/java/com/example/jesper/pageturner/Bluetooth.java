package com.example.jesper.pageturner;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
//import android.content.Intent;
//import android.os.Handler;
//import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class Bluetooth extends Activity
{
    //TextView myLabel;
    //EditText myTextbox;
    //BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket socket;
    BluetoothDevice device;
    //OutputStream OutputStream;
    InputStream InputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    //int counter;
    volatile boolean stopWorker;

    //Function for finding the bluetooth device
    void findBT()
    //Enables bluetooth on the android device
    //Turns it on if off, and searches for the arduino device
    {
        //BluetoothAdapter.getDefaultAdapter();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        /*if(mBluetoothAdapter == null)
            myLabel.setText("No bluetooth adapter available");
        */

        //If bluetooth is disabled it will be enabled
        if(!mBluetoothAdapter.isEnabled())
            mBluetoothAdapter.enable();

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("WhatUpBlueJuice"))
                {
                    this.device = device;
                    break;
                }
            }
        }
        //myLabel.setText("Bluetooth Device Found");
    }

    void openBT() throws IOException
    {
        //Opening in- and output for bluetooth on the android
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        socket = this.device.createRfcommSocketToServiceRecord(uuid);
        socket.connect();
        //OutputStream = socket.getOutputStream();
        InputStream = socket.getInputStream();
        beginListenForData();
        //myLabel.setText("Bluetooth Opened");
    }

    void beginListenForData() // Input from Arduino through bluetooth
    {
        //final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = InputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            InputStream.read(packetBytes);
                            for(int i = 0; i < bytesAvailable; i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    //final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    /*handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            //myLabel.setText(data);
                                        }
                                    });*/
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }
/*
        void sendData() throws IOException
        {
            String msg = myTextbox.getText().toString();
            msg += "\n";
            mmOutputStream.write(msg.getBytes());
            myLabel.setText("Data Sent");
        }

        void closeBT() throws IOException
        {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            myLabel.setText("Bluetooth Closed");
        }
*/

}