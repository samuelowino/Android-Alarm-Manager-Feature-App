package org.kazitek.androidalarm_labs.broadcastreceivers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.kazitek.androidalarm_labs.MainActivity;
import org.kazitek.androidalarm_labs.R;

public class GeneralAlarmNotificationsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "GeneralAlarmNotificatio";
    public static final String INTENT_ACTION = "general_alarm_notifications_intent_action";

    public GeneralAlarmNotificationsBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Toast.makeText(context,"General alarm receiver",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: action " + intent.getAction() );

        if (action != null && action.equalsIgnoreCase(INTENT_ACTION))
            showNotification(context);

    }

    private void showNotification(Context context) {

        Intent snoozeIntent = new Intent(context, MainActivity.class);
        Intent replyIntent = new Intent(context,MainActivity.class);

        PendingIntent replyPendingIntent = PendingIntent.getActivity(context,0,replyIntent,0);
        PendingIntent snoozePendingIntent = PendingIntent.getActivity(context,0,snoozeIntent,0);

        NotificationCompat.Builder notificationsBuilder = new NotificationCompat.Builder(context, "GENERAL_ALARM_CHANNEL")
                .setSmallIcon(R.drawable.ic_action_bell)
                .setContentTitle("Alarm Reminder")
                .setContentText("This is an alarm notification scheduled for now")
                .setAutoCancel(true)
                .addAction( new NotificationCompat.Action(android.R.drawable.star_on,"Snooze", snoozePendingIntent))
                .addAction( new NotificationCompat.Action(android.R.drawable.ic_menu_send,"Reply", replyPendingIntent))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = notificationsBuilder.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.createNotificationChannel(notificationChannel());
        notificationManagerCompat.notify((int) System.currentTimeMillis(),notification);

    }

    private NotificationChannel notificationChannel() {
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("GENERAL_ALARM_CHANNEL", "GENERAL ALARM CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Display notifications for any alarms alarms, when fired");
            notificationChannel.setShowBadge(true);
        }
        return notificationChannel;
    }
}
