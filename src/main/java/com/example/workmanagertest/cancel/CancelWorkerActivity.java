package com.example.workmanagertest.cancel;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workmanagertest.R;
import com.example.workmanagertest.constraints.ConstraintsWorker;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.State;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

public class CancelWorkerActivity extends AppCompatActivity {
    private Button mButtonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_worker);
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
        // 给任务设置tag->cancel
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(ConstraintsWorker.class).addTag("cancel").build();
        // 任务入队，WorkManager调度执行
        WorkManager.getInstance().enqueue(request);
        WorkManager.getInstance().getStatusById(request.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus workStatus) {
                if (workStatus == null) {
                    return;
                }
                if (workStatus.getState() == State.ENQUEUED) {
                    cancelWorker();
                }

            }
        });
    }

    /**
     * 通过tag取消任务
     */
    private void cancelWorker() {
        WorkManager.getInstance().cancelAllWorkByTag("cancel");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelWorker();
    }
}