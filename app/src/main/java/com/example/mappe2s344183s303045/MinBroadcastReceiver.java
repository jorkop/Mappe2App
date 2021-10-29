package com.example.mappe2s344183s303045;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED) || action.equals("com.example.mappe2s344183s303045")) {
            Intent i = new Intent(context, com.example.mappe2s344183s303045.SettPeriodiskService.class);
            context.startService(i);
        }else {
            Toast.makeText(context, "Broadcastreceiver funker ikke", Toast.LENGTH_SHORT).show();

        }
    }

}