package com.transcendence.blackhole.demo.didi.act;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.widget.ImageView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;


/**
 *
 * @author vigroid
 * @date 11/13/17
 */

public class DidiMainActivity extends TitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo_didi_splash;
    }

    @Override
    protected void init() {
        setTitle("滴滴Splash");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            final AnimatedVectorDrawable anim =
                    (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.demo_didi_anim);
            final ImageView logo = (ImageView) findViewById(R.id.logo);
            logo.setImageDrawable(anim);
            anim.start();
        }
    }
}
