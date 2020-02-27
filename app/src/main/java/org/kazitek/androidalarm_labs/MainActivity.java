package org.kazitek.androidalarm_labs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.kazitek.androidalarm_labs.adapter.ViewPagerAdapter;
import org.kazitek.androidalarm_labs.broadcastreceivers.AirplaneModeBroadcastReceiver;
import org.kazitek.androidalarm_labs.broadcastreceivers.GeneralAlarmNotificationsBroadcastReceiver;
import org.kazitek.androidalarm_labs.broadcastreceivers.RefreshSelectBroadcastReceiver;
import org.kazitek.androidalarm_labs.fragments.NonRepatingAlarmFragment;
import org.kazitek.androidalarm_labs.fragments.RepeatingAlarmFragment;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter adapter;

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

    NonRepatingAlarmFragment nonRepatingAlarmFragment;
    RepeatingAlarmFragment repeatingAlarmFragment;


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

        nonRepatingAlarmFragment = new NonRepatingAlarmFragment();
        repeatingAlarmFragment = new RepeatingAlarmFragment();

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
                nonRepatingAlarmFragment.setElapsedTime(true);
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
                nonRepatingAlarmFragment.setWakeUp(true);
            }
        });

        nonWakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(nonWakeUpButton);
                deActivateOptionButton(wakeUpButton);
                nonRepatingAlarmFragment.setWakeUp(false);
            }
        });

        repeatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(repeatingButton);
                deActivateOptionButton(exactRepeatingButton);
                deActivateOptionButton(nonRepeating);
                viewPager.setCurrentItem(1);
            }
        });

        exactRepeatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(exactRepeatingButton);
                deActivateOptionButton(repeatingButton);
                deActivateOptionButton(nonRepeating);

                viewPager.setCurrentItem(1);
            }
        });

        nonRepeating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activateOptionButton(nonRepeating);
                deActivateOptionButton(exactRepeatingButton);
                deActivateOptionButton(repeatingButton);
                viewPager.setCurrentItem(0);
            }
        });

        adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                nonRepatingAlarmFragment,
                repeatingAlarmFragment);

        viewPager.setAdapter(adapter);

        refereshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent refreshIntent = new Intent();
                refreshIntent.setAction(RefreshSelectBroadcastReceiver.INTENT_ACTION);
                sendBroadcast(refreshIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerAirplaneModeContextBroadcast();
        registerRefereshActionBroadcastReceiver();
        registerGeneralAlarmBroadcastReceiver();
    }

    private void activateOptionButton(Button elapsedRealtimeButton) {
        elapsedRealtimeButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        elapsedRealtimeButton.setTextColor(getResources().getColor(android.R.color.white));
    }

    private void deActivateOptionButton(Button elapsedRealtimeButton) {
        elapsedRealtimeButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        elapsedRealtimeButton.setTextColor(getResources().getColor(android.R.color.black));
    }

    private void registerAirplaneModeContextBroadcast(){
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        MainActivity.this.registerReceiver(new AirplaneModeBroadcastReceiver(),intentFilter);
    }

    private void registerRefereshActionBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter(RefreshSelectBroadcastReceiver.INTENT_ACTION);
        MainActivity.this.registerReceiver( new RefreshSelectBroadcastReceiver(),intentFilter);
    }

    private void registerGeneralAlarmBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter(GeneralAlarmNotificationsBroadcastReceiver.INTENT_ACTION);
        MainActivity.this.registerReceiver(new GeneralAlarmNotificationsBroadcastReceiver(),intentFilter);
    }
}
