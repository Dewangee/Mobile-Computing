package com.musicplayer.dewangee.musicplayer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Airplane extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String val=intent.getAction();
        Toast.makeText(context,val,Toast.LENGTH_SHORT).show();
    }
}
