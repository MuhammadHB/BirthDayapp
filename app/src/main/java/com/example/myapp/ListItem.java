package com.example.myapp;

public class ListItem {
    private int iconResId;
    private String text;
    private int soundResId; // Sound resource ID

    public ListItem(int iconResId, String text, int soundResId) {
        this.iconResId = iconResId;
        this.text = text;
        this.soundResId = soundResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getText() {
        return text;
    }

    public int getSoundResId() {
        return soundResId;
    }
}
