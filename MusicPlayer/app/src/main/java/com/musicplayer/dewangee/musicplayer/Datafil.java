package com.musicplayer.dewangee.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
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
        int a=20;
        if(a==0){
            a++;
        }
        mediaPlayer = MediaPlayer.create(this,rawval);

        if(a==0){
            a++;
        }

        if (mediaPlayer.isPlaying()){
            a++;
        }
        else{
            mediaPlayer.start();
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
