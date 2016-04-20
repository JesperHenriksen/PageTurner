package com.example.jesper.pageturner;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;
//import android.content.Intent;
//import android.os.Handler;
//import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Handler;

public class Bluetooth
{
    private static BluetoothSocket socket;
    private static BluetoothDevice device;

    public static int getData() {
        return data;
    }

    public static void setData(int data) {
        Bluetooth.data = data;
    }

    private static int data;
    protected final static int RECIEVE_MESSAGE = 1;
    private static StringBuilder sb = new StringBuilder();
    private static final String TAG = "bluetooth2";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID



    private static Thread workerThread;

    //Function for finding the bluetooth device
    public static void startBluetooth()
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
            for(BluetoothDevice devices : pairedDevices)
            {
                if(devices.getName().equals("WhatUpBlueJuice"))
                {
                    device = devices;
                    System.out.println("Bluetooth Device Found");
                    break;
                }
            }
        }
    }
    private static BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        //if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection", e);
            }
        //}
        return  device.createRfcommSocketToServiceRecord(MY_UUID);
    }
    public static void getArduinoData() throws IOException { // Input from Arduino through bluetooth
        //Opening in- and output for bluetooth on the android
        socket = createBluetoothSocket(device);
        socket.connect();
        System.out.println("Bluetooth Opened");
        System.out.println("Starting to listen for data...");
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                }
                catch(IOException e) {
                    e.printStackTrace();
                    return;
                }
                while(true) {
                    try {
                        //System.out.println("Starting to listen for data...1");
                        byte[] packetBytes = new byte[256];
                        //System.out.println("Starting to listen for data...2");
                        if(inputStream.available() > 1) {
                            setData(inputStream.read(packetBytes));
                            //System.out.println("Got data: " + bytes);
                        }
                        //ByteBuffer inputStreamDataBytes = ByteBuffer.wrap(packetBytes);

                        //System.out.println("Starting to listen for data...4");
                        //short data = inputStreamDataBytes.getShort();
                        //System.out.println("bluetooth input = " + data);
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });

        workerThread.start();
    }
}