package com.example.workmanagertest.constraints;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workmanagertest.R;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class ConstraintsWorkerActivity extends AppCompatActivity {
    private Button mButtonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraints_worker);
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
     * 启动约束任务
     */
    private void startWorker() {
        // 设置只有在wifi状态下才能执行
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build();
        // 设置约束条件
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(ConstraintsWorker.class).setConstraints(constraints)
                .addTag("constraint").build();
        // 任务入队，WorkManager调度执行
        WorkManager.getInstance().enqueue(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorkManager.getInstance().cancelAllWorkByTag("constraint");
    }
}