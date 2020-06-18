package com.example.liew.idelivery.Helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.example.liew.idelivery.R;

/**
 * Created by kundan on 1/20/2018.
 */

public class NotificationHelper extends ContextWrapper {

    private static final String TBC_CHANNEL_ID = "com.example.liew.idelivery.TechByteCare";
    private static final String TBC_CHANNEL_NAME = "EAT IT SERVER";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel tbcChannel = new NotificationChannel(TBC_CHANNEL_ID,TBC_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        tbcChannel.enableLights(false);
        tbcChannel.enableVibration(true);
        tbcChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(tbcChannel);
    }

    public NotificationManager getManager() {
        if (manager == null)    {
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getEatItChannelNotification(String title, String body, PendingIntent contentIntent, Uri soundUri)   {

        return new Notification.Builder(getApplicationContext(),TBC_CHANNEL_ID).setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getEatItChannelNotification(String title, String body, Uri soundUri)   {

        return new Notification.Builder(getApplicationContext(),TBC_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }
}
