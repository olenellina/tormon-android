package com.example.torrelaymonitoring;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Notifications extends Service {
    public Notifications() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

//  Start of my code, to add way to deal with messages
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        // TODO(developer): Handle FCM messages here.
//        // If the application is in the foreground handle both data and notification messages here.
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
//    }
}
