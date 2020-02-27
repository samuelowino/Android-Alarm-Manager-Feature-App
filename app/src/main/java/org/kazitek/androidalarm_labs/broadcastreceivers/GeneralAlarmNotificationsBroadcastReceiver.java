package org.kazitek.androidalarm_labs.broadcastreceivers;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.kazitek.androidalarm_labs.MainActivity;

public class GeneralAlarmNotificationsBroadcastReceiver extends BroadcastReceiver {

    public static final String INTENT_ACTION = "general_alarm_notifications_intent_action";

    public GeneralAlarmNotificationsBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null && action.equalsIgnoreCase(INTENT_ACTION))
            showNotification(context);

    }

    private void showNotification(Context context) {

        Intent snoozeIntent = new Intent(context, MainActivity.class);
        Intent replyIntent = new Intent(context,MainActivity.class);

        PendingIntent replyPendingIntent = PendingIntent.getActivity(context,0,replyIntent,0);
        PendingIntent snoozePendingIntent = PendingIntent.getActivity(context,0,snoozeIntent,0);

        NotificationCompat.Builder notificationsBuilder = new NotificationCompat.Builder(context, "ALARM_LABS_CHANNEL")
                .setContentTitle("Alarm Reminder")
                .setContentText("This is an alarm notification scheduled for now")
                .setAutoCancel(true)
                .addAction( new NotificationCompat.Action(android.R.drawable.star_on,"Snooze", snoozePendingIntent))
                .addAction( new NotificationCompat.Action(android.R.drawable.ic_menu_send,"Reply", replyPendingIntent))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = notificationsBuilder.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(111,notification);

    }
}
