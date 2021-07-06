package com.transcendence.blackhole.demo.permission.simple;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.blackhole.demo.permission.simple.permissionutils.SimplePermissionListener;
import com.transcendence.blackhole.demo.permission.simple.permissionutils.SimplePermissionUtils;
import com.transcendence.blackhole.R;

import java.util.List;

public class SimplePerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_other_per_main_simple);

        applyPermission();
    }


    //申请权限
    private void applyPermission(){
        findViewById(R.id.tv_checkPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击检查 相机、打电话 权限
                 */
                SimplePermissionUtils permissionUtil = new SimplePermissionUtils(SimplePerMainActivity.this);
                permissionUtil.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE},
                        new SimplePermissionListener() {
                            @Override
                            public void onGranted() {
                                //所有权限都已经授权
                                Toast.makeText(SimplePerMainActivity.this, "所有权限都已授权", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDenied(List<String> deniedPermission) {
                                //Toast第一个被拒绝的权限
                                Toast.makeText(SimplePerMainActivity.this, "拒绝了权限" + deniedPermission.get(0), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onShouldShowRationale(List<String> deniedPermission) {
                                //Toast第一个勾选不在提示的权限
                                Toast.makeText(SimplePerMainActivity.this, "这个权限" + deniedPermission.get(0)+"勾选了不在提示，要像用户解释为什么需要这权限", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }




}
