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

class SongAdapter extends ArrayAdapter{
    List list = new ArrayList();
    SongAdapter(Context context, int resource){
        super(context, resource);
    }

    static class DataHandler{
        ImageView note;
        TextView title;
        TextView artist;
    }
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        LayoutInflater songInflater;
        View row;
        row = contentView;
        DataHandler handler;
        if(contentView == null) {
            songInflater = LayoutInflater.from(getContext());
            row = songInflater.inflate(R.layout.row_layout, parent, false);
            handler = new DataHandler();
            handler.note = (ImageView) row.findViewById(R.id.note_image);
            handler.title = (TextView) row.findViewById(R.id.song_title);
            handler.artist = (TextView) row.findViewById(R.id.artist_name);
            row.setTag(handler);
        }
        else{
            handler = (DataHandler)row.getTag();
        }
        Song song;
        song = (Song)this.getItem(position);
        handler.note.setImageResource(song.getNoteImageResource());
        handler.title.setText(song.getSongTitle());
        handler.artist.setText(song.getArtist());

        return row;
    }
}
