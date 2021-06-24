package com.transcendence.blackhole.demo.other.act.badge;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.utils.SqBadgeUtils;
import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.core.global.Global;
import com.transcendence.core.utils.L;
import com.transcendence.core.utils.SPUtils;

/**
 * @Author Joephone on 2021/4/12 0012 上午 9:43
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BadgeActivity extends TitleBarActivity {

    private EditText mCountEditText;
    private int badgeCount = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_badge_sq;
    }

    @Override
    protected void init() {
        setTitle("App角标", "另一方案");
        mCountEditText = findViewById(R.id.et_count);

        findViewById(R.id.tv_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeCount = Integer.parseInt(mCountEditText.getText().toString());
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,Integer.parseInt(mCountEditText.getText().toString()));
                try {
                    if (SqBadgeUtils.setCount(badgeCount, mActivity)) {
                        Toast.makeText(mActivity, "设置成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                }
                clickHome();
            }
        });

        findViewById(R.id.tv_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeCount = Integer.parseInt(mCountEditText.getText().toString());
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,Integer.parseInt(mCountEditText.getText().toString()));
                try {
                    if (SqBadgeUtils.setCount(badgeCount, mActivity) && SqBadgeUtils.setNotificationBadge(badgeCount, mActivity)) {
                        Toast.makeText(mActivity, "设置成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                }
                clickHome();
            }
        });

        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().save(Global.SP_KEY.APP_BADGE,Integer.parseInt(mCountEditText.getText().toString()));
                startActivity(ZxBadgeActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        badgeCount =SPUtils.getInstance().get(Global.SP_KEY.APP_BADGE,99);
        L.d("onResume:"+badgeCount);
        mCountEditText.setText(badgeCount+"");
    }


    private void clickHome(){
        Intent intent= new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }
}
