package com.example.myapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_prefs";
    private static final String USER_NAME_KEY = "user_name";
    public static final String CHANNEL_ID = "user_notifications";  // Notification channel ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        Button btnToSecond = findViewById(R.id.btnToSecond);
        Button btnToList = findViewById(R.id.btnToList);

        // Create notification channel for Android 8.0 (API 26) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "User Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Schedule Eid notification
        scheduleEidNotification();

        // Check if a username is saved
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = prefs.getString(USER_NAME_KEY, null);

        if (savedName != null) {
            // If a username is saved, directly go to SecondActivity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("USER_NAME", savedName);
            startActivity(intent);
            finish();  // Close MainActivity
        }

        btnToSecond.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (!name.isEmpty()) {
                // Save the user's name in SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(USER_NAME_KEY, name);
                editor.apply();

                // Navigate to SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });

        btnToList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });
    }

    // Method to schedule the Eid notification
    private void scheduleEidNotification() {
        // Check if the app has permission to schedule exact alarms
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!getSystemService(AlarmManager.class).canScheduleExactAlarms()) {
                // Show a message or prompt the user to grant the permission
                Toast.makeText(this, "Permission required to schedule exact alarms.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Set the date to Eid (April 9th, 2025)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2025);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);  // Months are 0-based (April = 3)
        calendar.set(Calendar.DAY_OF_MONTH, 28);  // Eid date
        calendar.set(Calendar.HOUR_OF_DAY, 12);  // Set time for the notification (e.g., 9:00 AM)
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        // Check if the set date is in the future
        if (calendar.before(Calendar.getInstance())) {
            // If the date is already passed, set it for next year
            calendar.add(Calendar.YEAR, 1);
        }

        // Create an Intent to show the notification
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("TITLE", "Eid Mubarak!");
        intent.putExtra("MESSAGE", "Wishing you a blessed Eid!");

        // Create a PendingIntent to trigger the notification
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Set up the AlarmManager to trigger the notification at the scheduled time
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
}