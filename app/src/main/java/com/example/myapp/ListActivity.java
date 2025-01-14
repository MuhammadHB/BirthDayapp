package com.example.myapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Find ListView in layout
        ListView listView = findViewById(R.id.list_view);

        // Prepare data (15 items with icons, text, and sound resources)
        ArrayList<ListItem> items = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int soundResId = getSoundResourceForItem(i);
            items.add(new ListItem(R.drawable.ic_launcher_foreground, "Item " + (i + 1), soundResId));
        }

        // Set custom adapter
        ListItemAdapter adapter = new ListItemAdapter(this, items);
        listView.setAdapter(adapter);

        // Find the button and set an OnClickListener to navigate back to MainActivity
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(v -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // This method will return a sound resource ID based on the item position
    private int getSoundResourceForItem(int position) {
        switch (position) {
            case 0: return R.raw.sound1;
            case 1: return R.raw.sound2;
            case 2: return R.raw.sound3;
            case 3: return R.raw.sound4;
            case 4: return R.raw.sound5;
            case 5: return R.raw.sound6;
            case 6: return R.raw.sound7;
            case 7: return R.raw.sound8;
            case 8: return R.raw.sound9;
            case 9: return R.raw.sound10;
            case 10: return R.raw.sound11;
            case 11: return R.raw.sound12;
            case 12: return R.raw.sound13;
            case 13: return R.raw.sound14;
            case 14: return R.raw.sound15;
            default: return R.raw.sound1; // Default sound if out of range
        }
    }
}
