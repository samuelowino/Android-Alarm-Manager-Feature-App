package org.kazitek.androidalarm_labs.fragments;

import android.app.AlarmManager;
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
import androidx.fragment.app.Fragment;

import org.kazitek.androidalarm_labs.MainActivity;
import org.kazitek.androidalarm_labs.R;

public class NonRepatingAlarmFragment extends Fragment {

    private Button fireAtButton;
    private Button setAlarmButton;

    private Boolean isElapsedTime;
    private Boolean isWakeUp;

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
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            }
                        },
                        12,
                        44,
                        false

                );

                timePickerDialog.show();


            }
        });

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 32, getPendingIntent());
            }
        });

        return view;
    }

    private PendingIntent getPendingIntent() {

        Intent intent = new Intent(getContext(),MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,0);

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(getContext(), "DEFAULT_CHANNLE")
                .setContentTitle("Non Repeating Alarm Fired")
                .setContentText("This is a non repeating one time alarm")
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_launcher_background
                        , "Reply", pendingIntent))
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_launcher_background,
                        "Snooze", pendingIntent))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        return pendingIntent;

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