package com.example.jesper.pageturner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SongAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public SongAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    static class DataHandler{
        ImageView note;
        TextView title;
        TextView artist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            handler = new DataHandler();
            handler.note = (ImageView)row.findViewById(R.id.note_image);
            handler.title = (TextView)row.findViewById(R.id.Song_title);
            handler.artist = (TextView)row.findViewById(R.id.Artist_name);
            row.setTag(handler);

        }
        else {
            handler = (DataHandler)row.getTag();
        }
        Song song;
        song = (Song)this.getItem(position);
        handler.note.setImageResource(song.getNoteImageResource());
        handler.title.setText(song.getSongTitle());
        handler.artist.setText(song.getArtistName());

        return row;
    }
}
