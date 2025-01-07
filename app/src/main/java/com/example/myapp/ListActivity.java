package com.example.myapp;

import android.content.Intent;
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

        // Prepare data
        ArrayList<ListItem> items = new ArrayList<>();
        items.add(new ListItem(R.drawable.ic_launcher_foreground, "MUHAMMAD BUNYARIT"));
        items.add(new ListItem(R.drawable.ic_launcher_foreground, "ANAS IBN BATOTAH"));
        items.add(new ListItem(R.drawable.ic_launcher_foreground, "BADER AL ZAHRANI"));

        // Set custom adapter
        ListItemAdapter adapter = new ListItemAdapter(this, items);
        listView.setAdapter(adapter);

        // Find the button and set an OnClickListener to navigate back to MainActivity
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
