package com.example.threads_and_async_services;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView txtNumber;
    private SecondAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ExampleAsyncTask asyncTask = new ExampleAsyncTask();
//
//        asyncTask.execute(10);
        asyncTask = new SecondAsyncTask();
        asyncTask.execute(10);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != asyncTask){
            if (!asyncTask.isCancelled()){
                asyncTask.cancel(true);
            }
        }
    }

    public class SecondAsyncTask extends AsyncTask<Integer,Integer,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtNumber=findViewById(R.id.txtNumber);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                publishProgress(i);
                SystemClock.sleep(1000);
            }
            return "Finished";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtNumber.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtNumber.setText(s);
        }
    }
}