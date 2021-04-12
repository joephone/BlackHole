package com.transcendence.blackhole.demo.other.act.badge;

import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.utils.ZxBadgeCountUtils;
import com.transcendence.blackhole.demo.other.utils.ZxBadgeUtils;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @Author Joephone on 2021/4/12 0012 上午 10:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ZxBadgeActivity extends TitleBarActivity implements View.OnClickListener {

    public static int badgeCount; //角标数量

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_badge_zx;
    }

    @Override
    protected void init() {
        setTitle("另一方案");
        findViewById(R.id.tv_badge_count_reduce).setOnClickListener(this);
        findViewById(R.id.tv_badge_count_add).setOnClickListener(this);
        findViewById(R.id.tv_badge_count_reduce2).setOnClickListener(this);

        //设置角标数量
        ZxBadgeUtils.setBadgeCount(this,10);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_badge_count_reduce:
                //Android原生方式 角标数量减1
                ZxBadgeUtils.setBadgeCount(this,--badgeCount);
                finish();
                break;
            case R.id.tv_badge_count_add:
                //第三方库 角标数量加1
                ZxBadgeCountUtils.setBadgeCount(this,++badgeCount);
                finish();
                break;
            case R.id.tv_badge_count_reduce2:
                //第三方库 角标数量减1
                ZxBadgeCountUtils.setBadgeCount(this,--badgeCount);
                finish();
                break;
        }
    }
}
