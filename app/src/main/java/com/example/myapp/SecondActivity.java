package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the name passed from MainActivity
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        // Display the welcome message
        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        if (userName != null && !userName.isEmpty()) {
            welcomeMessage.setText("Happy Eid, " + userName + "!");
        } else {
            welcomeMessage.setText("Happy Eid!");
        }

        // Set up the button to navigate back to MainActivity
        Button btnToFirst = findViewById(R.id.btnToFirst);
        btnToFirst.setOnClickListener(v -> {
            // Navigate back to MainActivity
            Intent backIntent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(backIntent);
        });
    }
}
