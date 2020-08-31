package com.mapp.androidcomponents.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mapp.androidcomponents.BroadcastReceiver.BroadcastReceiverActivity;
import com.mapp.androidcomponents.ContentProvider.ContentProviderActivity;
import com.mapp.androidcomponents.R;
import com.mapp.androidcomponents.Service.ServiceExample;
import com.mapp.androidcomponents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnActivity.setOnClickListener(this);
        binding.btnService.setOnClickListener(this);
        binding.btnBroadcast.setOnClickListener(this);
        binding.btnContent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActivity:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.btnService:
                Intent iService = new Intent(this, ServiceExample.class);
                startActivity(iService);
                break;
            case R.id.btnBroadcast:
                Intent iBroadcast = new Intent(this, BroadcastReceiverActivity.class);
                startActivity(iBroadcast);
                break;
            case R.id.btnContent:
                Intent iContent = new Intent(this, ContentProviderActivity.class);
                startActivity(iContent);
                break;
        }

    }
}