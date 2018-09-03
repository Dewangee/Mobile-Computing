package com.musicplayer.dewangee.musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import static android.provider.Telephony.Mms.Part.FILENAME;

public class ServiceDownload extends Service {

        public ServiceDownload() {

        }

        @Override
        public IBinder onBind(Intent intent) {

            return null;
        }
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            new DownloadMusic().execute();
            return START_STICKY;
        }

        public void onDestroy() {

            Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }

        class DownloadMusic extends AsyncTask<String, Void, String> {


            private String readIt(InputStream is, int len) throws IOException, UnsupportedEncodingException {
                Reader reader = null;
                reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[len];
                reader.read(buffer);
                return new String(buffer);

            }


            @Override
            protected String doInBackground(String... strings) {
                try {
                    InputStream is = null;
                    OutputStream out = null;
                    int a=20;
                    if(a==0){
                        a++;
                    }
                    int len = 100000000;
                    try {
                        java.net.URL url = new URL("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                        HttpURLConnection connectiondone = (HttpURLConnection) url.openConnection();
                        connectiondone.connect();
                        is = connectiondone.getInputStream();


                        String Value = readIt(is, len);

                        out = openFileOutput(FILENAME, Context.MODE_PRIVATE);

                        out.write(Value.getBytes());

                        out.close();

                        return Value;

                    } finally {
                        if (is != null) {
                            Log.i("Download", "Done");
                            is.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

        }



}
