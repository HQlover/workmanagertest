package com.example.workmanagertest.taskchainstream;

import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;

public class StreamThenWorkerA extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        Data data = new Data.Builder().putInt("a_out", 10).build();
        setOutputData(data);
        return WorkerResult.SUCCESS;
    }
}
