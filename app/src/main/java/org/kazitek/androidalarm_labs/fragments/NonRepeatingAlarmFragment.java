package org.kazitek.androidalarm_labs.fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import org.kazitek.androidalarm_labs.MainActivity;
import org.kazitek.androidalarm_labs.R;
import org.kazitek.androidalarm_labs.broadcastreceivers.GeneralAlarmNotificationsBroadcastReceiver;

import java.util.Calendar;

public class NonRepeatingAlarmFragment extends Fragment {

    private Button fireAtButton;
    private Button setAlarmButton;

    private Boolean isElapsedTime;
    private Boolean isWakeUp;

    private Calendar alarmTimeCalendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_repating_alarm, container, false);
        fireAtButton = view.findViewById(R.id.non_repating_alarm_time_button);
        setAlarmButton = view.findViewById(R.id.non_repating_set_alarm_button);

        fireAtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                alarmTimeCalendar = Calendar.getInstance();
                                alarmTimeCalendar.set(Calendar.HOUR_OF_DAY, hour);
                                alarmTimeCalendar.set(Calendar.MINUTE, minute);
                            }
                        },
                        alarmTimeCalendar.get(Calendar.HOUR_OF_DAY),
                        alarmTimeCalendar.get(Calendar.MINUTE),
                        false

                );

                timePickerDialog.show();


            }
        });

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notificationReceiverIntent = new Intent(getContext(), GeneralAlarmNotificationsBroadcastReceiver.class);
                notificationReceiverIntent.setAction(GeneralAlarmNotificationsBroadcastReceiver.INTENT_ACTION);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), (int) System.currentTimeMillis(), notificationReceiverIntent, 0);

                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeCalendar.getTimeInMillis(), pendingIntent);

                showAlarmSetNotification();
            }
        });

        return view;
    }

    private void showAlarmSetNotification() {

        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), "REPEATING_ALARM_CHANNEL")
                .setContentTitle("You have set a Non-repeating alarm")
                .setContentText("Non Repeating alarm set for : ")
                .setContentText("Minute " + alarmTimeCalendar.get(Calendar.MINUTE) + " Hour : " + alarmTimeCalendar.get(Calendar.HOUR_OF_DAY))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_action_bell)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_action_bell, "Okay", pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.createNotificationChannel(notificationChannel());
        notificationManagerCompat.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }

    private NotificationChannel notificationChannel() {
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("REPEATING_ALARM_CHANNEL", "REPEATING ALARM CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Display notifications for non-repeating alarms");
            notificationChannel.setShowBadge(true);
        }
        return notificationChannel;
    }

    public Boolean getElapsedTime() {
        return isElapsedTime;
    }

    public void setElapsedTime(Boolean elapsedTime) {
        isElapsedTime = elapsedTime;
    }

    public Boolean getWakeUp() {
        return isWakeUp;
    }

    public void setWakeUp(Boolean wakeUp) {
        isWakeUp = wakeUp;
    }
}