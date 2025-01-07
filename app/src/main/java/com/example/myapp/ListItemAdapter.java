package com.example.myapp;

import android.content.Context;
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

        // Set the play sound icon (static for now)
        ImageView playIcon = convertView.findViewById(R.id.play_icon);
        playIcon.setImageResource(R.drawable.playicon);

        return convertView;
    }
}
