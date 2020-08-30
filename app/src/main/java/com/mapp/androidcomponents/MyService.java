package com.mapp.androidcomponents;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MyService extends Service{

    MediaPlayer mPlayer;
    IBinder binder;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();
        binder = new SoundBinder();
        mPlayer = MediaPlayer.create(this, R.raw.bensound_creativeminds);

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Services started", Toast.LENGTH_LONG).show();
        playAudio();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed!", Toast.LENGTH_LONG).show();
        mPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;

    }

    public void playAudio(){
            mPlayer.start();
    }

    //Class used to return the service.
    public class SoundBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
