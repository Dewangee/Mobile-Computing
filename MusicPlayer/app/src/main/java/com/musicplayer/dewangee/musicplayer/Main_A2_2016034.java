package com.musicplayer.dewangee.musicplayer;
import android.app.Service;
import android.content.*;
import android.net.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main_A2_2016034 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__a2_2016034);

        Bootcompleted breceiver = new Bootcompleted();
        Airplane areceiver =new Airplane();
        PowerConnected preceiver=new PowerConnected();


        this.registerReceiver(breceiver,new IntentFilter(Intent.ACTION_BOOT_COMPLETED));

        this.registerReceiver(areceiver,new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

        this.registerReceiver(preceiver,new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

    }

    public void Download(View v)
    {
        Log.i("Button","Button CLicked");
        ConnectivityManager connManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connManager.getActiveNetworkInfo();
        Log.i("Button","Network Checking...");
        if(networkInfo!=null && networkInfo.isConnected())
        {
            Log.i("Button","Network connected :)");
            Toast.makeText(this,"Network connected :)",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, ServiceDownload.class);
            startService(intent);
        }
        else{
            Log.i("Button","Network disconnected :(");
            Toast.makeText(this,"Network disconnected :(",Toast.LENGTH_SHORT).show();
        }
    }

}
