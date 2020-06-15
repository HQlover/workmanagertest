package com.example.workmanagertest.taskchainstream;

import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;

public class StreamCombineWorkerA extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        Data data = new Data.Builder().putInt("b_key", 200).putInt("a_key", 200).build();
        setOutputData(data);
        return WorkerResult.SUCCESS;
    }
}
