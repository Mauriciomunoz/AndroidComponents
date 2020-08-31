package com.mapp.androidcomponents.BroadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.mapp.androidcomponents.R;
import com.mapp.androidcomponents.databinding.ActivityBroadcastReceiverBinding;

public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    MyReceiver myReceiver;
    ActivityBroadcastReceiverBinding binding;

    @Override
    protected void onStop() {
        unregisterReceiver(myReceiver);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_broadcast_receiver);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_broadcast_receiver);
        binding.btnActivateReceiver.setOnClickListener(this);
        binding.btnSendBroadcast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnActivateReceiver:
                //Some actions can be set in the AndroidManifest.xml. Some others just work adding in code
                //This example works when the app detects a change of connectivity and airplane is activated.
                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

                myReceiver = new MyReceiver();
                registerReceiver(myReceiver, filter);
                break;
            case R.id.btnSendBroadcast:

                //Create the custom filter to be able to catch it later
                //This is helpful when we want to communicate with other activities or fragments.
                IntentFilter customFilter = new IntentFilter("CustomAction");
                myReceiver = new MyReceiver();
                registerReceiver(myReceiver, customFilter);

                //Send the custom action
                Intent customIntent = new Intent();
                customIntent.setAction("CustomAction");
                sendBroadcast(customIntent);

                break;

        }
    }
}