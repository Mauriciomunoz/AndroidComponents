package com.mapp.androidcomponents.Service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.mapp.androidcomponents.R;
import com.mapp.androidcomponents.databinding.ActivityServiceExampleBinding;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceExample extends AppCompatActivity implements View.OnClickListener {

    private ActivityServiceExampleBinding binding;
    Intent i;
    MyService soundService;
    boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_example);
        binding.btnPlay.setOnClickListener(this);
        binding.btnStop.setOnClickListener(this);
        binding.btnPause.setOnClickListener(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.SoundBinder myBinder = (MyService.SoundBinder)service;
            //Get service from bind
            soundService = myBinder.getService();

            if(soundService.mPlayer != null) {
                //Got the duration to show it in a text
                int duration = soundService.mPlayer.getDuration();
                int seconds = duration / 1000;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                binding.musicDuration.setText("" + minutes + ":" + seconds);

                //A cheaty way to get the current position of the song
                //Each second got the position and show it.
                //It can be improved using a custom media player and having a progress bar with listener
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int position = soundService.mPlayer.getCurrentPosition();
                                int seconds = position / 1000;
                                int minutes = seconds / 60;
                                seconds = seconds % 60;
                                if(seconds < 10)
                                    binding.musicPos.setText("" + minutes + ":0" + seconds);
                                else
                                    binding.musicPos.setText("" + minutes + ":" + seconds);
                            }
                        });
                    }
                }, 0, 1000);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPlay:

                //Attach the service to our Activity
                //Bound Service
                i = new Intent(this, MyService.class);
                bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
                this.startService(i);


                //This is a simple call to the service.
                //Started service. We won't receive any information from the service
                /*
                i = new Intent(this, MyService.class);
                this.startService(i);
                */

                //You can test this code to see the difference.
                //If the app is send to background. The sound will stop. In a service this behavior is not happening
                /*
                MediaPlayer mp = MediaPlayer.create(this, R.raw.bensound_creativeminds);
                mp.setLooping(false);
                mp.start();*/
                break;
            case R.id.btnStop:
                //Destroy de service
                if(isMyServiceRunning(MyService.class)) {
                    i = new Intent(this, MyService.class);
                    unbindService(serviceConnection);
                    this.stopService(i);
                    binding.musicPos.setText("0:00");
                }else{
                    Toast.makeText(this, "Audio is not playing", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnPause:
                //Using the binded service we can get the MediaPlayer and pause it
                //We can call again the bind service and the song will continue but this will be binding and creating intents
                if(soundService != null) {
                    if (!isPaused) {
                        soundService.mPlayer.pause();
                        isPaused = true;
                    } else {
                        soundService.playAudio();
                        isPaused = false;
                    }
                }else{
                    Toast.makeText(this, "Audio is not playing", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}