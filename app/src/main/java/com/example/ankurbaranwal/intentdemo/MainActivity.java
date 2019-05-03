package com.example.ankurbaranwal.intentdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    Button b1;
    static final String myChannel = "My Channel";
    static final int myID = 1;
    public static final String riKey = " reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 =(Button)findViewById(R.id.button);
    }
    public void showNotification(View view) {

        Intent i = new Intent(this, SecondActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, i, PendingIntent.FLAG_ONE_SHOT);

        Intent i1 = new Intent(this, MainActivity.class);
        PendingIntent pi1 = PendingIntent.getActivity(this, 2, i1, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, myChannel);
        builder.setSmallIcon(R.drawable.ic_sms_failed_black_24dp);
        builder.setContentTitle("My Notification");
        builder.setContentText("This is my notification msg");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setContentIntent(pi);
        
        builder.addAction(R.drawable.check,"ok..",pi);
        builder.addAction(R.drawable.cancel, "Cancel",pi1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {

            RemoteInput ri = new RemoteInput.Builder(riKey).setLabel("Reply").build();

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.check,"Replying",pi).addRemoteInput(ri).build();
            builder.addAction(action);
        }

        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);

        managerCompat.notify(myID, builder.build());
    }
}
