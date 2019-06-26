package com.transcendence.blackhole.ui.base.act;

import android.view.KeyEvent;
import android.view.WindowManager;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;


/**
 * @author Joephone on 2019/6/18 17:33
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 禁用Home键
 * @Edition 1.0
 * @EditionHistory
 */

public class LockerHomeActivity extends TitleBarActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        setTitle("禁用Home键");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        L.d("onKeyDown: keyCode -- " + keyCode);
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override

    public void onAttachedToWindow() {
        //TYPE_KEYGUARD
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();

    }

}
