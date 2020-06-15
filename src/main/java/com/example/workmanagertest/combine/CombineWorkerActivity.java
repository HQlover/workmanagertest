package com.example.workmanagertest.combine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workmanagertest.R;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

public class CombineWorkerActivity extends AppCompatActivity {
    private Button mButtonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_worker);
        initView();
        initEvent();
    }

    private void initView() {
        mButtonStart = findViewById(R.id.button_start);
    }

    private void initEvent() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorker();
            }
        });
    }

    /**
     * 组合任务
     */
    private void startWorker() {
        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder( CombineWorkerA.class)
                .addTag("combine").build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(CombineWorkerB.class)
                .addTag("combine").build();
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(CombineWorkerC.class)
                .addTag("combine").build();
        OneTimeWorkRequest requestD = new OneTimeWorkRequest.Builder(CombineWorkerD.class)
                .addTag("combine").build();
        OneTimeWorkRequest requestE = new OneTimeWorkRequest.Builder(CombineWorkerE.class)
                .addTag("combine").build();
        //A,B任务链
        WorkContinuation continuationAB = WorkManager.getInstance().beginWith(requestA).then(requestB);
        //C,D任务链
        WorkContinuation continuationCD = WorkManager.getInstance().beginWith(requestC).then(requestD);
        //合并上面两个任务链，在接入requestE任务，入队执行
        WorkContinuation.combine(continuationAB, continuationCD).then(requestE).enqueue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorkManager.getInstance().cancelAllWorkByTag("combine");
    }

}