package com.mapp.androidcomponents.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        //Using android:exported="true" in AndroidManifest we can do accessible for external apps our receiver
        //I mean, if some other app send our Custom Action we can catch it and do something.
        //This is helpful for deep links or something similar.
        if(intent.getAction() == "android.net.conn.CONNECTIVITY_CHANGE") {
            Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Action: Custom action", Toast.LENGTH_SHORT).show();
        }
    }
}
