package com.transcendence.structure.framework;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.transcendence.structure.activity.KeyBoardActivity;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Joephone on 2021/9/7 0007 上午 11:54
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class RootActivity extends ListActivity {

    private String[] mTitles = new String[]{
            "XhsEmoticonsKeyboard仿qq微信键盘"
    };

    private Class[] mActivities = new Class[]{
            KeyBoardActivity.class
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));
//        LogUtils.e("id===push===:" + JPushInterface.getRegistrationID(BaseApplication.mBaseApplication));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this, mActivities[position]));
    }


    /**
     * 单击回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitSystem();
        }
        return false;
    }

    /**
     * 双击退出
     */
    private static Boolean isExit = false;
    /**
     * 双击退出
     */
    private void exitSystem() {
        // 准备退出
        ScheduledExecutorService service = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            service = new ScheduledThreadPoolExecutor(2);
            service.schedule(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2, TimeUnit.SECONDS);
        }else {
            finish();
            System.exit(0);
        }
    }
}
