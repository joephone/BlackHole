package com.transcendence.core.base.activity;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.transcendence.core.utils.log.LogUtils;

/**
 * @author joephone
 * @date 2023/2/20
 * @desc 业务Activity
 */
public abstract class AppAc extends AbsTitleBarAc {


    public void showMsg(@NonNull String msg){
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }


    public void startAc(Class<?> cls) {
        LogUtils.d("startAc:"+cls.getName());
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

//    public void startAc(Class<?> cls, Bundle bundle) {
//        Intent intent = new Intent(this, cls);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    public void startAcForResult(Class<?> cls, int requestCode) {
//        Intent intent = new Intent(this, cls);
//        startActivityForResult(intent, requestCode);
//    }

}
