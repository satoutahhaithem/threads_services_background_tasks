package com.example.startedservices;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class ExampleService extends IntentService {
    private static final String TAG = "ExampleService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ExampleService() {
        super("Donwload Thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            int number = intent.getIntExtra("number",-1);
            for (int i = 0; i < number; i++) {
                Log.d(TAG, "onHandleIntent: i was "+i);
                SystemClock.sleep(1000);
            }
        }
    }
}
