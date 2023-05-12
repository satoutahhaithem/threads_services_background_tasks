package com.example.startedservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class SecondExampleService extends Service {
    private int startId=-1;
    private static final String TAG = "SecondExampleService";
    public SecondExampleService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId=startId;
        if (null != intent){
            int number = intent.getIntExtra("number",-1);
            if (number != -1 ){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < number; i++) {
                            Log.d(TAG, "run:  is was "+ i);
                            SystemClock.sleep(1000);
                        }
                    }
                });
                thread.start();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf(startId);
    }
}