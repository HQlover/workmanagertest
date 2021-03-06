package com.example.workmanagertest.intputoutput;

import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;

public class IntputOutputWorker extends Worker {

    @NonNull
    @Override
    public WorkerResult doWork() {
        try {
            //模拟耗时任务
            Thread.sleep(3000);
            Data inputData = getInputData();
            //获取到输入的参数，我们又把输入的参数给outputData
            Data outputData = new Data.Builder().putString("key_name", inputData.getString("key_name", "no data")).build();
            setOutputData(outputData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return WorkerResult.SUCCESS;
    }
}
