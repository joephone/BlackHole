package com.transcendence.blackhole.demo.other.act;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.view.InputMethodLayout;
import com.transcendence.core.base.activity.TitleBarActivity;

public class InputMethodFirstActivity extends TitleBarActivity {
    private static final String TAG = "MainActivity";

    InputMethodLayout rl;
    LinearLayout ll;

    int scrollViewHeight;
    private ImageView iv;
    private ScrollView scrollView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_other_firstactivity;
    }


    protected void init() {
        rl = (InputMethodLayout) findViewById(R.id.rl);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        rl.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case InputMethodLayout.KEYBOARD_STATE_HIDE:

                        break;
                    case InputMethodLayout.KEYBOARD_STATE_SHOW:

                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollViewHeight = scrollView.getMeasuredWidth();
                                scrollView.scrollTo(0, 65535);
                            }
                        });

                        break;

                    default:
                        break;
                }
            }
        });


    }


}
