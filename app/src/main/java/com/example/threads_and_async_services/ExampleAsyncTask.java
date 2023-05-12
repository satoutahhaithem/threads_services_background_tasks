package com.example.threads_and_async_services;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class ExampleAsyncTask extends AsyncTask<Integer, Void, Void> {
    private static final String TAG = "ExampleAsyncTask";
    @Override
    protected Void doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++) {
            Log.d(TAG, "doInBackground: i was " + i);
            SystemClock.sleep(1000);
        }
        return null;
    }
}
