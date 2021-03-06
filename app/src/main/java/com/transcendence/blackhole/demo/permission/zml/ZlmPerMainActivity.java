package com.transcendence.blackhole.demo.permission.zml;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.transcendence.blackhole.R;

/**
 * description: test
 * author: zlm
 * date: 2017/3/17 16:01
*/
public class ZlmPerMainActivity extends ZlmPerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_other_per_main_zml);

    }

    public void requestSinglePermission(View view){
        requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},new RequestPermissionCallBack() {
            @Override
            public void granted() {
                Toast.makeText(ZlmPerMainActivity.this, "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
            }

            @Override
            public void denied() {
                Toast.makeText(ZlmPerMainActivity.this, "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void requestMultiPermission(View view){
        requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_CONTACTS},
                new RequestPermissionCallBack() {
            @Override
            public void granted() {
                Toast.makeText(ZlmPerMainActivity.this, "获取权限成功，执行正常操作", Toast.LENGTH_LONG).show();
            }

            @Override
            public void denied() {
                Toast.makeText(ZlmPerMainActivity.this, "获取权限失败，正常功能受到影响", Toast.LENGTH_LONG).show();
            }
        });
    }
}
