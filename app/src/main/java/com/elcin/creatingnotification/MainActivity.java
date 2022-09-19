package com.elcin.creatingnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonNotification;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotification = (Button) findViewById(R.id.button);

        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                situational();

            }
        });
    }

    public void situational() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(MainActivity.this, ToMeetScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channelId";
            String channelName = "channelName";
            String channelDescription = "channelDescription";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);

            if (channel == null) {
                channel = new NotificationChannel(channelId, channelName, kanalOnceligi);
                channel.setDescription(channelDescription);
                notificationManager.createNotificationChannel(channel);

            }
            builder = new NotificationCompat.Builder(this, channelId);

            builder.setContentTitle("Title");
            builder.setContentText("Text");
            builder.setSmallIcon(R.drawable.cookie);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);

        } else {
            builder = new NotificationCompat.Builder(this);

            builder.setContentTitle("Title");
            builder.setContentText("Text");
            builder.setSmallIcon(R.drawable.cookie);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);

        }
        notificationManager.notify(1,builder.build());
    }
}