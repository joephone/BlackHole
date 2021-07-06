package com.transcendence.blackhole.demo.umeng;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.transcendence.blackhole.R;
import com.transcendence.core.umeng.BaseUmengThirdLoginAndShareActivity;

public class UmengThirdLoginAndShareActivity extends BaseUmengThirdLoginAndShareActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_umeng_thirdlogin_share_main);
    }

    public void qq(View view) {
        super.qq(view);
    }

    public void weiXin(View view) {
        super.weiXin(view);
    }

    public void weixinCircle(View view) {
        super.weixinCircle(view);
    }

    public void sina(View view) {
        super.sina(view);
    }

    public void Qzone(View view) {
        super.Qzone(view);
    }


    public void qqLogin(View view) {
        super.qqLogin(view);
    }

    public void weiXinLogin(View view) {
        super.weiXinLogin(view);
    }

    public void sinaLogin(View view) {
        super.sinaLogin(view);
    }
}
