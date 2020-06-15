package com.example.workmanagertest.taskchainstream;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;

public class StreamThenWorkerC extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        Data inputData = getInputData();
        int b_out = inputData.getInt("b_out", 0);
        //获取到B任务的输出，我们只是做一个简单的输出。
        Log.d("huang", "value = " + b_out);
        return WorkerResult.SUCCESS;
    }
}
