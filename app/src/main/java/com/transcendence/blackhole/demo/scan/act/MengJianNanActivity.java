package com.transcendence.blackhole.demo.scan.act;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.scan.view.MengJianNanScanView;

public class MengJianNanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_scan_main);
        final MengJianNanScanView scanView = (MengJianNanScanView) findViewById(R.id.scan);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanView.setVisibility(View.VISIBLE);
                scanView.start();
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanView.setVisibility(View.GONE);
                scanView.close();
            }
        });
    }
}
