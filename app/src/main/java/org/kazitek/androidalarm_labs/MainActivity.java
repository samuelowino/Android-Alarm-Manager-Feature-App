package org.kazitek.androidalarm_labs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    ViewPager viewPager;
    Button refereshButton;
    Button elapsedRealtimeButton;
    Button realtimeClockButton;
    Button wakeUpButton;
    Button nonWakeUpButton;
    Button repeatingButton;
    Button exactRepeatingButton;
    Button nonRepeating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        viewPager = findViewById(R.id.user_inputs_view_pager);
        refereshButton = findViewById(R.id.referesh_selection_button);
        elapsedRealtimeButton = findViewById(R.id.elapsed_realtime_button);
        realtimeClockButton = findViewById(R.id.realtime_clock_rtc_button);
        wakeUpButton = findViewById(R.id.wake_up_button);
        nonWakeUpButton = findViewById(R.id.non_wake_up_button);
        repeatingButton = findViewById(R.id.repeating_button);
        exactRepeatingButton = findViewById(R.id.exact_repating_button);
        nonRepeating = findViewById(R.id.non_repeating_button);

        refereshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(refereshButton);
            }
        });

        elapsedRealtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(elapsedRealtimeButton);
                deActivateOptionButton(realtimeClockButton);
            }
        });

        realtimeClockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(realtimeClockButton);
                deActivateOptionButton(elapsedRealtimeButton);
            }
        });

        wakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(wakeUpButton);
                deActivateOptionButton(nonWakeUpButton);
            }
        });

        nonWakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(nonWakeUpButton);
                deActivateOptionButton(wakeUpButton);
            }
        });

        repeatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(repeatingButton);
                deActivateOptionButton(exactRepeatingButton);
                deActivateOptionButton(nonRepeating);
            }
        });

        exactRepeatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(exactRepeatingButton);
                deActivateOptionButton(repeatingButton);
                deActivateOptionButton(nonRepeating);
            }
        });

        nonRepeating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(nonRepeating);
                deActivateOptionButton(exactRepeatingButton);
                deActivateOptionButton(repeatingButton);
            }
        });

    }

    private void activateOptionButton(Button elapsedRealtimeButton) {
        elapsedRealtimeButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        elapsedRealtimeButton.setTextColor(getResources().getColor(android.R.color.white));
    }

    private void deActivateOptionButton(Button elapsedRealtimeButton) {
        elapsedRealtimeButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        elapsedRealtimeButton.setTextColor(getResources().getColor(android.R.color.black));
    }
}
