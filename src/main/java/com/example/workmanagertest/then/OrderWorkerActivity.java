package com.example.workmanagertest.then;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workmanagertest.R;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class OrderWorkerActivity extends AppCompatActivity {
    private Button mButtonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_worker);
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
     * A,B,C三个任务顺序执行
     */
    private void startWorker() {
        // A
        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(OrderWorkerA.class)
                .addTag("order").build();
        // B
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(OrderWorkerB.class)
                .addTag("order").build();
        // C
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(OrderWorkerC.class)
                .addTag("order").build();
        // 任务入队，WorkManager调度执行
        WorkManager.getInstance().beginWith(requestA).then(requestB).then(requestC).enqueue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorkManager.getInstance().cancelAllWorkByTag("order");
    }
}