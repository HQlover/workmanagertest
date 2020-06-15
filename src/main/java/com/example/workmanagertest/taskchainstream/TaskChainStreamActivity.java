package com.example.workmanagertest.taskchainstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workmanagertest.R;

import androidx.work.ArrayCreatingInputMerger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

public class TaskChainStreamActivity extends AppCompatActivity {
    private Button mButtonThen;
    private Button mButtonCombine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_chain_stream);
        initView();
        initEvent();
    }

    private void initView() {
        mButtonThen = findViewById(R.id.button_stream_then);
        mButtonCombine = findViewById(R.id.button_stream_combine);
    }

    private void initEvent() {
        mButtonThen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThenWorker();
            }
        });
        mButtonCombine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCombineWorker();
            }
        });
    }


    /**
     * 顺序任务的数据流
     * A,B,C三个任务。A,输出10，B任务得到A任务的值×10，最后给到C任务。
     */
    private void startThenWorker() {
        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(StreamThenWorkerA.class)
                .addTag("chain").build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(StreamThenWorkerB.class)
                .addTag("chain").build();
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(StreamThenWorkerC.class)
                .addTag("chain").build();
        WorkManager.getInstance().beginWith(requestA).then(requestB).then(requestC).enqueue();
    }

    /**
     * 在C任务中获取到A,B任务的输出
     */
    private void startCombineWorker() {
        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(StreamCombineWorkerA.class)
                .addTag("chain").build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(StreamCombineWorkerB.class)
                .addTag("chain").build();
        // 设置合并规则OverwritingInputMerger
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(StreamCombineWorkerC.class)
                .addTag("chain").setInputMerger(
                OverwritingInputMerger.class).build();
//        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(StreamCombineWorkerC.class)
//                .addTag("chain").setInputMerger(
//                        ArrayCreatingInputMerger.class).build();
        //A任务链
        WorkContinuation continuationA = WorkManager.getInstance().beginWith(requestA);
        //B任务链
        WorkContinuation continuationB = WorkManager.getInstance().beginWith(requestB);
        //合并上面两个任务链，在接入requestE任务，入队执行
        WorkContinuation continuation = WorkContinuation.combine(continuationA, continuationB).then(requestC);
        continuation.enqueue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorkManager.getInstance().cancelAllWorkByTag("chain");
    }

}