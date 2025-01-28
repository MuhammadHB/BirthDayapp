package com.example.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_prefs";
    private static final String USER_NAME_KEY = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the username passed from MainActivity
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        if (userName != null && !userName.isEmpty()) {
            welcomeMessage.setText("Happy Eid, " + userName + "!");
        } else {
            welcomeMessage.setText("Happy Eid!");
        }

        // Button to navigate back to MainActivity and delete saved username
        Button btnToFirst = findViewById(R.id.btnToFirst);
        btnToFirst.setOnClickListener(v -> {
            // Delete the saved username from SharedPreferences
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(USER_NAME_KEY);
            editor.apply();

            // Navigate back to MainActivity
            Intent backIntent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish();  // Close SecondActivity
        });
    }
}
