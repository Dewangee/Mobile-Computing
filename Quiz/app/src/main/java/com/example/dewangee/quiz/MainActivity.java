package com.example.dewangee.quiz;

import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     File gl;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_UPLOAD_PROGRESS = 0;


    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        // dismissDialog(0);

        Detail detail = new Detail();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(manager.findFragmentById(R.id.detail_frag)).commit();
    }


    public ArrayList<Question> getList() {
        db = new DatabaseHelper(this);
        ArrayList<Question> question_list = new ArrayList<Question>();
        int length = db.numberofquestions();
        for (int i = 1; i <= length; i++) {
            Question question = new Question();
            question.setId(i);
            question.setStmt(db.getQuestion(i));
            question_list.add(question);
        }
        return question_list;

    }

    public void onSubmit(View view) {

        checkNetConnection();


    }


    public void checkNetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        Log.i("Button", "Network Checking...");
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i("Button", "Network connected :)");
            Toast.makeText(this, "Network connected :)", Toast.LENGTH_SHORT).show();
            convertoCSV();
            new UploadCSV(this).execute();

        } else {
            Log.i("Button", "Network disconnected :(");
            Toast.makeText(this, "Network disconnected :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void convertoCSV() {

        DatabaseHelper db = new DatabaseHelper(this);

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "Result.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor curCSV = database.rawQuery("SELECT * FROM questions", null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while (curCSV.moveToNext()) {
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            gl = file;

        } catch (Exception e) {
            Log.i("hello", "123");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    class UploadCSV extends AsyncTask<String, Void, String> {


        Context context;

        UploadCSV(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_UPLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... strings) {

            String urlString = "http://192.168.59.89:8000/";
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = gl;

            if (sourceFile.isFile()) {

                try {

                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(urlString);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"myfile\";filename=\"" + gl.getAbsolutePath() + "\"" + lineEnd);

                    dos.writeBytes(lineEnd);
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];
                    int sentBytes = 0;


                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {

                        dos.write(buffer, 0, bufferSize);
                        sentBytes += bufferSize;
                        publishProgress("" + (int) (sentBytes * 100 / bytesAvailable));
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    int serverResponseCode = conn.getResponseCode();
                    if (serverResponseCode == 200) {


                    }
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (Exception e) {
                    //dismissDialog(DIALOG_DOWNLOAD_PROGRESS);

                    e.printStackTrace();

                }


            }


            return null;


        }

        private void publishProgress(String... progress) {
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String s) {

            dismissDialog(DIALOG_UPLOAD_PROGRESS);
            super.onPostExecute(s);
        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_UPLOAD_PROGRESS: //we set this to 0
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Uploading");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setMax(100);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

}















