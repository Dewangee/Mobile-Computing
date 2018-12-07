package com.musicplayer.dewangee.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class Datafil extends Service{


    public Datafil(){

    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        Bundle bundle=intent.getExtras();
        int rawval=bundle.getInt("SongMusic");
        Log.i("check", "123123123");
        mediaPlayer = MediaPlayer.create(this,rawval);


        if (mediaPlayer.isPlaying()){
        }
        else{
            mediaPlayer.start();
            Log.i("check", "1231123");
        }

        return START_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onDestroy(){
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }





}
