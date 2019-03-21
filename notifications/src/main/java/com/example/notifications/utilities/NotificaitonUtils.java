package com.example.notifications.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;
import com.example.android.background.sync.ReminderTasks;
import com.example.android.background.sync.WaterReminderIntentService;
import com.example.notifications.R;
import com.example.notifications.sync.ReminderTasks;

public class NotificaitonUtils {


    private static final String NOTIFICIATION_CHANNEL_ID="reminder_notification_channel";
    private static final int WATER_REMINDER_NOTIFICATION_ID=2417;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID=6432;

    private static final int WATER_REMINDER_PENDING_INTENT_ID=3417;

public static void clearNotifications(Context context)
{
    NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancelAll();
}


    public static void remindUserBecauseCharging(Context context) {


        // completed (7) Create a method called remindUserBecauseCharging which takes a Context.
        // This method will create a notification for charging. It might be helpful
        // to take a look at this guide to see an example of what the code in this method will look like:
        // https://developer.android.com/training/notify-user/build-notification.html
        // completed (8) Get the NotificationManager using context.getSystemService
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // completed (9) Create a notification channel for Android O devices
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICIATION_CHANNEL_ID,context.getString(R.string.main_notificaiton_channel),NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        // completed (10) In the remindUserBecauseCharging method use NotificationCompat.Builder to create a notification

        // that:
        // - has a color of R.color.colorPrimary - use ContextCompat.getColor to get a compatible color
        // - has ic_drink_notification as the small icon
        // - uses icon returned by the largeIcon helper method as the large icon
        // - sets the title to the charging_reminder_notification_title String resource
        // - sets the text to the charging_reminder_notification_body String resource
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
        // - sets the notification defaults to vibrate
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
        // - automatically cancels the notification when the notification is clicked
        NotificationCompat.Builder notificaitonBuilder = new NotificationCompat.Builder(context,NOTIFICIATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(LargeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE|Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);


        // completed (11) If the build version is greater than or equal to JELLY_BEAN and less than OREO,
        // set the notification's priority to PRIORITY_HIGH.
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT<Build.VERSION_CODES.O)
        {
            notificaitonBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }


        // completed (12) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()
notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID,notificaitonBuilder.build());
    }

    private static NotificationCompat.Action ignoreRemainderAction(Context context)
    {
        Intent ignoreReminderIntent = new Intent(context, WaterReminderIntentService.class);

        ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICAITON);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px, "No, Thanks.",ignoreReminderPendingIntent);
        return ignoreReminderAction;

    }














    // completed (1) Create a helper method called contentIntent with a single parameter for a Context. It


    private static PendingIntent contentIntent(Context context)
    {

Intent startActivityIntent= new Intent(context , MainActivity.class);
        // should return a PendingIntent. This method will create the pending intent which will trigger when
        // the notification is pressed. This pending intent should open up the MainActivity.
        // completed (2) Create an intent that opens up the MainActivity
        Intent StartActivityIntent = new Intent(context,MainActivity.class);
        // completed (3) Create a PendingIntent using getActivity that:
        // - Take the context passed in as a parameter
        // - Takes an unique integer ID for the pending intent (you can create a constant for
        //   this integer above
        // - Takes the intent to open the MainActivity you just created; this is what is triggered
        //   when the notification is triggered
        // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
        // intent but update the data

        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

    }
        // completed (4) Create a helper method called largeIcon which takes in a Context as a parameter and
        // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
        // completed (5) Get a Resources object from the context.
        // completed (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and R.drawable.ic_local_drink_black_24px

private static Bitmap LargeIcon(Context context){

        Resources res = context.getResources();

        Bitmap LargeIcon = BitmapFactory.decodeResource(res,R.drawable.ic_local_drink_black_24px);
        return LargeIcon;
}
    }
