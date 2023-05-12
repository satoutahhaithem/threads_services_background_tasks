package com.example.bound_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private boolean isBound=false;

    private TextView textViewRandomNumber;
    private SampleService myService;
    private DisplayRandomAsyncTask asyncTask;
    private ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SampleService.LocalBinder binder =(SampleService.LocalBinder) service;
            myService = binder.getService();
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewRandomNumber=findViewById(R.id.txtRandomNumber);
        asyncTask= new DisplayRandomAsyncTask();
        asyncTask.execute(10);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this ,SampleService.class);
        bindService(intent,connection,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound && myService!=null){
            unbindService(connection);
        }

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

    public class DisplayRandomAsyncTask extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++) {
                if (isBound && null!=myService){
                    publishProgress(myService.getRandom());
                }
                SystemClock.sleep(1000);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewRandomNumber.setText(String.valueOf(values[0]));
        }
    }
}