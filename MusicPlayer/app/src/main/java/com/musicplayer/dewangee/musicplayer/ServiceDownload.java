package com.musicplayer.dewangee.musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
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


                    int len = 100000000;
                    try {
                        java.net.URL url = new URL("http://faculty.iiitd.ac.in/~mukulika/s1.mp3");
                        HttpURLConnection connectiondone = (HttpURLConnection) url.openConnection();
                        Log.i("Download", "Connection opened");
                        connectiondone.connect();
                        Log.i("Download", "Connected to server");
                        is = connectiondone.getInputStream();

                        Log.i("Download", "Song downloaded");

                        String FILENAME = "downloadedsong.mp3";
                       String Value = readIt(is, len);
                      FileOutputStream out = null;


                        try{

                        out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                         Log.i("Download", "Downloaded song saved to file");
                        out.write(Value.getBytes());
                        Log.i("Download","Downloaded written");
                        out.close();
                        }
                        catch(Exception e){

                        }
//                            int count;
//                        File file = new File(getFilesDir(),FILENAME);
//                        out = new FileOutputStream(file);
//                        byte data[] = new byte[1024];
//
//                        long total = 0;
//
//                        while ((count = is.read(data)) != -1) {
//                            total += count;
//
//                            out.write(data, 0, count);
//                        }
//                        out.flush();
//                        out.close();
//



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
                Log.i("Download", "onPostExecute run");
            }

        }



}
