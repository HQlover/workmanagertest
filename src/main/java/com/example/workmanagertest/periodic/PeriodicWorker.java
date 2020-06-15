package com.example.workmanagertest.periodic;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;

public class PeriodicWorker extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        try {
            //模拟耗时任务
            Thread.sleep(1000);
            Log.d("huang", "periodic work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return WorkerResult.SUCCESS;
    }
}
