package com.transcendence.blackhole.ui.image.saveimgtogallery;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.utils.L;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;

import java.util.List;



public class SaveToGallaryActivity extends TitleBarActivity  {

    private static final int REQUEST_CODE_SAVE_IMG = 10;
    private static final String TAG = "MainActivity";
    private Context mContext;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_save_to_gallary;
    }

    @Override
    protected void init() {
        findViewById(R.id.save_img_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d("onClick");
                requestPermission();
            }
        });
    }

    /**
     * 请求读取sd卡的权限
     */
    private void requestPermission() {
        Permissions.with(mActivity)
                .permission(PermissionPool.MANAGE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        L.d("onGranted");
                        saveImage();
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {

                    }
                });

//        if (Build.VERSION.SDK_INT >= 23) {
//            //读取sd卡的权限
//            String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            if (EasyPermissions.hasPermissions(mContext, mPermissionList)) {
//                //已经同意过
//                saveImage();
//            } else {
//                //未同意过,或者说是拒绝了，再次申请权限
//                EasyPermissions.requestPermissions(
//                        this,  //上下文
//                        "保存图片需要读取sd卡的权限", //提示文言
//                        REQUEST_CODE_SAVE_IMG, //请求码
//                        mPermissionList //权限列表
//                );
//            }
//        } else {
//            saveImage();
//        }
    }


    //保存图片
    private void saveImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.beauty06);
        boolean isSaveSuccess = ImgUtils.saveImageToGallery(mContext, bitmap);
        if (isSaveSuccess) {
            Toast.makeText(mContext, "保存图片成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    //授权结果，分发下去
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // Forward results to EasyPermissions
//        //跳转到onPermissionsGranted或者onPermissionsDenied去回调授权结果
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }


    //同意授权
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> list) {
//        Log.i(TAG, "onPermissionsGranted:" + requestCode + ":" + list.size());
//        saveImage();
//    }

    //拒绝授权
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Log.i(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            //打开系统设置，手动授权
//            new AppSettingsDialog.Builder(this).build().show();
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
//            //拒绝授权后，从系统设置了授权后，返回APP进行相应的操作
//            Log.i(TAG, "onPermissionsDenied:------>自定义设置授权后返回APP");
//            saveImage();
//        }
//    }
}
