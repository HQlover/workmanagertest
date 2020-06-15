package com.example.workmanagertest.taskchainstream;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;

public class StreamCombineWorkerC extends Worker {
    @NonNull
    @Override
    public WorkerResult doWork() {
        Data data = getInputData();

        // 注意;这里我用的是getIntArray
        int[] aKeyValueList = data.getIntArray("a_key");
        int[] bKeyValueList = data.getIntArray("b_key");
        for(int i=0;i<aKeyValueList.length;i++){
            Log.d("huang", "a_key"+i+" = " + aKeyValueList[i]);
        }
        for(int i=0;i<bKeyValueList.length;i++){
            Log.d("huang", "b_key"+i+" = " + bKeyValueList[i]);
        }

        return WorkerResult.SUCCESS;
    }
}
