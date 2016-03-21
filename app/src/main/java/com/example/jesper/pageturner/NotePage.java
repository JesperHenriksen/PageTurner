package com.example.jesper.pageturner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.SpinnerAdapter;

public class NotePage extends Activity {

    String[] options = {"Reset Song", "Show  Sheet Music", "Show Progress Bar"};
    int arr_images[] = {R.drawable.backbutton, R.drawable.backbutton, R.drawable.backbutton};

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        spinner = (Spinner)findViewById(R.id.dropDownMenu);
        spinner.setAdapter(new SpinnerAdapter(this, R.layout.spinner_layout, options));
    }

    public class SpinnerAdapter extends ArrayAdapter<String>{
        public SpinnerAdapter(Context context, int textViewResourceId, String[] objects){
            super(context, textViewResourceId, objects);
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            return getSpinnerView(position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            return getSpinnerView(position, convertView, parent);
        }

        public View getSpinnerView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.spinner_layout, parent, false);
            TextView label= (TextView)row.findViewById(R.id.menuItems);
            label.setText(options[position]);

            ImageView icon=(ImageView)row.findViewById(R.id.spinnerIcons);
            return row;
        }
    }

    public void backChooseSong(View view)
    {
        Intent intent = new Intent(NotePage.this, ChooseSong.class);
        startActivity(intent);
    }

}
