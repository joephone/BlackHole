package com.transcendence.guard.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @Author Joephone on 2021/2/10 0010 上午 9:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GuardBaseActivity extends AppCompatActivity {

    private long mExitTIme;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - mExitTIme > 2000){
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                mExitTIme = System.currentTimeMillis();
            }else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
