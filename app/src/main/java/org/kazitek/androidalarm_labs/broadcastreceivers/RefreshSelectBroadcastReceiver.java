package org.kazitek.androidalarm_labs.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class RefreshSelectBroadcastReceiver extends BroadcastReceiver {

    public static final String INTENT_ACTION = "refresh_select_intent_action";

    public RefreshSelectBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null && intentAction.equalsIgnoreCase(INTENT_ACTION))
            Toast.makeText(context, "Received Referesh Select Broadcast", Toast.LENGTH_SHORT).show();
    }
}
