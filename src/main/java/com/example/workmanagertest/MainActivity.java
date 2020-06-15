package com.example.workmanagertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.workmanagertest.cancel.CancelWorkerActivity;
import com.example.workmanagertest.combine.CombineWorkerActivity;
import com.example.workmanagertest.constraints.ConstraintsWorkerActivity;
import com.example.workmanagertest.intputoutput.IntputOutoutWorkerActivity;
import com.example.workmanagertest.periodic.PeriodicWorkerActivity;
import com.example.workmanagertest.taskchainstream.TaskChainStreamActivity;
import com.example.workmanagertest.then.OrderWorkerActivity;
import com.example.workmanagertest.unique.UniqueWorkerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CancelWorkerActivity.class));
            }
        });

        findViewById(R.id.button_combine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CombineWorkerActivity.class));
            }
        });

        findViewById(R.id.button_constraints).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConstraintsWorkerActivity.class));
            }
        });

        findViewById(R.id.button_intervals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PeriodicWorkerActivity.class));
            }
        });

        findViewById(R.id.button_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrderWorkerActivity.class));
            }
        });

        findViewById(R.id.button_output).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IntputOutoutWorkerActivity.class));
            }
        });

        findViewById(R.id.button_task_charin_stream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TaskChainStreamActivity.class));
            }
        });

        findViewById(R.id.button_task_unique).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UniqueWorkerActivity.class));
            }
        });

    }
}
