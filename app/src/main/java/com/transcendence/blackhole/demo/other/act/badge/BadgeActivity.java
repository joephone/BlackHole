package com.transcendence.blackhole.demo.other.act.badge;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.demo.other.utils.SqBadgeUtils;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @Author Joephone on 2021/4/12 0012 上午 9:43
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BadgeActivity extends TitleBarActivity {

    private EditText mCountEditText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_badge_sq;
    }

    @Override
    protected void init() {
        setTitle("App角标", "另一方案");
        mCountEditText = findViewById(R.id.et_count);
        ZxBadgeActivity.badgeCount = Integer.parseInt(mCountEditText.getText().toString());
        findViewById(R.id.tv_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ZxBadgeActivity.badgeCount = Integer.parseInt(mCountEditText.getText().toString());
                    if (SqBadgeUtils.setCount(ZxBadgeActivity.badgeCount, mActivity)) {
                        Toast.makeText(mActivity, "设置成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.tv_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ZxBadgeActivity.badgeCount = Integer.parseInt(mCountEditText.getText().toString());
                    if (SqBadgeUtils.setNotificationBadge(ZxBadgeActivity.badgeCount, mActivity)) {
                        Toast.makeText(mActivity, "设置成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mActivity, "设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ZxBadgeActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ZxBadgeActivity.badgeCount >= 0) {
            mCountEditText.setText(ZxBadgeActivity.badgeCount + "");
        }
    }
}
