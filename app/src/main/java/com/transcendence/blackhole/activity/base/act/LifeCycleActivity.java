package com.transcendence.blackhole.activity.base.act;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;

/**
 * @author Joephone on 2019/5/27 14:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  生命周期
 * @Edition 1.0
 * @EditionHistory
 */

public class LifeCycleActivity extends TitleBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
        ToastUtils.show("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
    }



    @Override
    protected void onStart() {
        super.onStart();
        L.d("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
        ToastUtils.show("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d("onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
        ToastUtils.show("onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
    }


    @Override
    protected void onResume() {
        super.onResume();
        L.d("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
        ToastUtils.show("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("4 activity is running");
            }
        }, 1000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        L.d("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
        ToastUtils.show("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
        ToastUtils.show("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放");
        ToastUtils.show("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放");
    }


    @Override
    public void finish() {
        // TODO Auto-generated method stub
        L.d("finish()");
//        if (!PublicClass.Exception) {
//            ActivityStackManager.activityStackManager.pop();
//        }
        super.finish();
    }


    TextView mTvTab;

    @Override
    public void init() {
        setTitle("生命周期");
        mTvTab = findViewById(R.id.tvTab);
        mTvTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LifeCycleTwoActivity.class);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_life_cycle;
    }
}
