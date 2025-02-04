package com.example.myapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "user_notifications";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Create notification channel (for Android 8.0 and above)
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "User Notifications",
                    NotificationManager.IMPORTANCE_HIGH // Set HIGH importance
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Create and show the notification
        String title = intent.getStringExtra("TITLE");
        String message = intent.getStringExtra("MESSAGE");

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.nav)  // Make sure this is a valid drawable
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(0, notification);  // Show the notification with ID 0
    }
}
