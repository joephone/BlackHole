package com.transcendence.guard.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.SlidingDrawer;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.transcendence.guard.R;
import com.transcendence.guard.adapter.HomeAdapter;
import com.transcendence.guard.dialog.DialogSetUpPw;
import com.transcendence.guard.listener.DialogCallBack;
import com.transcendence.guard.listener.XUtilCallBack;

import java.io.File;

/**
 * @Author Joephone on 2021/2/9 0009 上午 11:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class HomeActivity extends GuardBaseActivity {

    private GridView mGvHome;
    private HomeAdapter mHomeAdapter;
    private SlidingDrawer sd;
    /**
     * 设备管理员
     */
    private DevicePolicyManager policyManager;
    /**
     * 申请权限
     */
    private ComponentName componentName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_guard);

        sd = (SlidingDrawer) findViewById(R.id.sd);
        mGvHome = findViewById(R.id.gv_home);
        mHomeAdapter = new HomeAdapter(HomeActivity.this);
        mGvHome.setAdapter(mHomeAdapter);
        //1获取设备管理员
        policyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //申请权限
//        componentName = new ComponentName(this,MyDevice);
        //3判断，如果没有权限则申请权限
        boolean active = policyManager.isAdminActive(componentName);
        if(!active){
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"获取超级管理员权限，用于远程锁屏和清除数据");
            startActivity(intent);
        }
    }


    /**
     * 弹出设置密码对话框
     */
    private void showSetUpPwdDialog(){
        final DialogSetUpPw dialogSetUpPw = new DialogSetUpPw(HomeActivity.this);
        dialogSetUpPw.setCallBack(new DialogCallBack() {
            @Override
            public void ok() {

            }

            @Override
            public void cancel() {

            }
        });
    }

    /**
     * 检测menu键打开抽屉
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (sd.isOpened()) {
                sd.close();
            } else if (!sd.isOpened()) {
                sd.open();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
