package com.musicplayer.dewangee.musicplayer;

import android.content.*;
import android.util.Log;
import android.widget.Toast;

public class PowerConnected extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String val=intent.getAction();
        Toast.makeText(context,val,Toast.LENGTH_SHORT).show();
        Log.i("charge" ,"123");
    }
}