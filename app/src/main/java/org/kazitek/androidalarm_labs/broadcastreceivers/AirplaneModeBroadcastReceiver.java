package org.kazitek.androidalarm_labs.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "AirplaneModeBroadcastRe";

    public AirplaneModeBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: Intent " + intent);
        Toast.makeText(context,"Aipplane Mode Broad cast received",Toast.LENGTH_SHORT).show();
    }
}
