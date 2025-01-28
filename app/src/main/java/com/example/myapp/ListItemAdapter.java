package com.example.myapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListItemAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> items;
// constractar of LISTITEMADAPTER class 
    public ListItemAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        // Get current item
        ListItem currentItem = (ListItem) getItem(position);

        // Set the icon
        ImageView iconView = convertView.findViewById(R.id.item_icon);
        iconView.setImageResource(currentItem.getIconResId());

        // Set the text
        TextView textView = convertView.findViewById(R.id.item_text);
        textView.setText(currentItem.getText());

        // Set the play sound icon
        ImageView playIcon = convertView.findViewById(R.id.play_icon);
        playIcon.setImageResource(R.drawable.playicon);

        // Handle play button click
        playIcon.setOnClickListener(v -> {
            // Play a different sound depending on the position
            int soundResId = getSoundForPosition(position);

            // Play the sound using MediaPlayer
            MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResId);
            mediaPlayer.start();

            // Release MediaPlayer resources after playback is finished
            mediaPlayer.setOnCompletionListener(mp -> mp.release());
        });

        return convertView;
    }

    // Method to return the sound resource ID for each item based on position
    private int getSoundForPosition(int position) {
        switch (position) {
            case 0:
                return R.raw.sound1;
            case 1:
                return R.raw.sound2;
            case 2:
                return R.raw.sound3;
            case 3:
                return R.raw.sound4;
            case 4:
                return R.raw.sound5;
            case 5:
                return R.raw.sound6;
            case 6:
                return R.raw.sound7;
            case 7:
                return R.raw.sound8;
            case 8:
                return R.raw.sound9;
            case 9:
                return R.raw.sound10;
            case 10:
                return R.raw.sound11;
            case 11:
                return R.raw.sound12;
            case 12:
                return R.raw.sound13;
            case 13:
                return R.raw.sound14;
            case 14:
                return R.raw.sound15;
            default:
                throw new IllegalArgumentException("Sound not found for position: " + position); // Or return -1 if you prefer
        }
    }


}
