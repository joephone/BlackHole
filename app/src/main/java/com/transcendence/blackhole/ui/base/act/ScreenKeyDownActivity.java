package com.transcendence.blackhole.ui.base.act;

import android.view.KeyEvent;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;


/**
 * Created by Joephone on 2018/12/13 16:57
 * E-Mail Address：joephonechen@gmail.com
 * Android 回退键监听
 * @author
 */

public class ScreenKeyDownActivity extends TitleBarActivity {



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        setTitle("Android回退键监听");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        L.d("onKeyDown: keyCode -- " + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                L.d("KeyEvent.KEYCODE_BACK");
//                break;
                //拦截事件
                return false;
            case KeyEvent.KEYCODE_MENU:
                L.d("KeyEvent.KEYCODE_MENU");
                //                break;
                //拦截事件
                return false;
            case KeyEvent.KEYCODE_HOME:
                L.d( "KeyEvent.KEYCODE_HOME");
                //                break;
                //拦截事件
                return false;
            case KeyEvent.KEYCODE_APP_SWITCH:
                L.d( "KeyEvent.KEYCODE_APP_SWITCH");
                // 收不到
                //                break;
                //拦截事件
                return false;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
