package com.transcendence.blackhole.demo.other.act.badge;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.utils.ZxBadgeCountUtils;
import com.transcendence.blackhole.demo.other.utils.ZxBadgeUtils;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.SPUtils;

/**
 * @Author Joephone on 2021/4/12 0012 上午 10:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ZxBadgeActivity extends TitleBarActivity implements View.OnClickListener {

    private int badgeCount; //角标数量

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

        badgeCount = SPUtils.getInstance().get(Global.SP_KEY.APP_BADGE,99);
        //设置角标数量
        ZxBadgeUtils.setBadgeCount(this,badgeCount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_badge_count_reduce:
                //Android原生方式 角标数量减1
                ZxBadgeUtils.setBadgeCount(this,--badgeCount);
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,badgeCount);
                clickHome();
                break;
            case R.id.tv_badge_count_add:
                //第三方库 角标数量加1
                ZxBadgeCountUtils.setBadgeCount(this,++badgeCount);
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,badgeCount);
                clickHome();
                break;
            case R.id.tv_badge_count_reduce2:
                //第三方库 角标数量减1
                ZxBadgeCountUtils.setBadgeCount(this,--badgeCount);
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,badgeCount);
                clickHome();
                break;
            case R.id.fl_back:
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,badgeCount);
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,badgeCount);
            finish();
            //不执行父类点击事件
            return true;
        }
        //继续执行父类其他点击事件
        return super.onKeyUp(keyCode, event);
    }


    private void clickHome(){
        Intent intent= new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }

}
