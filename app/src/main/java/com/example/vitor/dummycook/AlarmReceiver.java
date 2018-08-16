package com.example.vitor.dummycook;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

        Intent notificationIntent = new Intent(context, DetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("recipeSelected", extras.getSerializable("recipe"));

        notificationIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);


        // Passar os parametros da mensagem
        //Bundle extras = intent.getExtras();
        Recipe r1 = (Recipe) extras.getSerializable("recipe");

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(extras.getInt("index"), PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);


        Notification notification = builder.setContentTitle(r1.getName())
                .setContentText(r1.getTextIngredients())
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(r1.getTextIngredients()))
                .build();



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(extras.getInt("index"), notification);
    }
}
