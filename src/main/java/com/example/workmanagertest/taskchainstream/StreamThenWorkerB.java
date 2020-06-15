package com.example.workmanagertest.taskchainstream;

import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;

public class StreamThenWorkerB extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        //先得到A任务的输出值
        Data inputData = getInputData();
        int a_out = inputData.getInt("a_out", 0);
        //把A任务的输出×10在给到C任务
        Data data = new Data.Builder().putInt("b_out", a_out * 10).build();
        setOutputData(data);
        return WorkerResult.SUCCESS;
    }
}
