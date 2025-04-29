package com.transcendence.freeland.ble.test;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.transcendence.core.base.activity.AppAc;
import com.transcendence.core.utils.log.LogUtils;
import com.transcendence.freeland.R;

/**
 * @author joephone
 * @date 2023/6/3
 * @desc
 */
public class BleTest extends AppAc {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_ble_firsttest;
    }

    @Override
    protected void initView() {
        openBlueTooth();
    }


    //获取蓝牙适配器
    public static BluetoothAdapter mBluetoothAdapter = null;
    //蓝牙状态监听广播
    public class BlueToothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_TURNING_ON:
                            LogUtils.d("onReceive---------蓝牙正在打开中");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            LogUtils.d("onReceive---------蓝牙已经打开");
                            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            LogUtils.d("onReceive---------蓝牙正在关闭中");
                            break;
                        case BluetoothAdapter.STATE_OFF:
                            LogUtils.d("onReceive---------蓝牙已经关闭");
                            break;
                    }
                    break;
            }
        }
    }
    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    /**
     * public static boolean isGranted(Context context, Permission permission) {
     *         return ContextCompat.checkSelfPermission(context, permission.toString()) == PackageManager.PERMISSION_GRANTED;
     *     }
     * @return
     */
    //Android12蓝牙权限申请
    private boolean bluePermission(){
        //compileSdkVersion项目中编译SDK版本大于30申请以下权限可使用
        //Manifest.permission.BLUETOOTH_SCAN、Manifest.permission.BLUETOOTH_ADVERTISE、Manifest.permission.BLUETOOTH_CONNECT
        //若小于30可以直接使用权限对应的字符串
        LogUtils.d("当前Android版本："+Build.VERSION.SDK_INT);
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
//            if (ContextCompat.checkSelfPermission(this,
//                    "android.permission.BLUETOOTH_SCAN")
//                    != PackageManager.PERMISSION_GRANTED
//                    || ContextCompat.checkSelfPermission(this,
//                    "android.permission.BLUETOOTH_ADVERTISE")
//                    != PackageManager.PERMISSION_GRANTED
//                    || ContextCompat.checkSelfPermission(this,
//                    "android.permission.BLUETOOTH_CONNECT")
//                    != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(this,new String[]{
//                        "android.permission.BLUETOOTH_SCAN",
//                        "android.permission.BLUETOOTH_ADVERTISE",
//                        "android.permission.BLUETOOTH_CONNECT"}, 1);
//                return false;
//            }
//        }else{
            if (ContextCompat.checkSelfPermission(this,
                    "android.permission.ACCESS_FINE_LOCATION")
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{
                        "android.permission.ACCESS_FINE_LOCATION"}, 1);
                return false;
            }
//        }
        return true;
    }
    private void openBlueTooth(){
        if (bluePermission()){
            LogUtils.d("打开了蓝牙权限");
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }else{
            LogUtils.d("没开 return");
            return;
        }

        BlueToothReceiver btlr = new BlueToothReceiver();
        this.registerReceiver(btlr, makeFilter());
        if (!mBluetoothAdapter.isEnabled()) {// 判断是否打开蓝牙
            showMsg("请先开启蓝牙!");
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(intent, SEARCH_CODE);
            // 不做提示，强行打开
            mBluetoothAdapter.enable();
        }
    }

}
