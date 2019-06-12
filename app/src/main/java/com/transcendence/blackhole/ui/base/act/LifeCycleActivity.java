package com.transcendence.blackhole.ui.base.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joephone on 2019/5/27 14:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  生命周期
 * @Edition 1.0
 * @EditionHistory
 */

public class LifeCycleActivity extends TitleBarActivity implements AdapterView.OnItemClickListener {
    private final int onCreate =1;
    private final int onStart =2;
    private final int onResume =3;
    private final int onRunning =4;
    private final int onPause =5;
    private final int onStop =6;
    private final int onDestroy =7;

    private final int onRestart =8;

    private ArrayAdapter<String> adapter;
    private ListView lvIndex;
    List<String> items = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case onCreate:
                    items.add("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
                    adapter.notifyDataSetChanged();
                    break;
                case onStart:
                    items.add("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
                    adapter.notifyDataSetChanged();
                    break;
                case onResume:
                    items.add("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
                    adapter.notifyDataSetChanged();
                    break;
                case onRunning:
                    items.add("4 Activity is running");
                    adapter.notifyDataSetChanged();
                    break;
                case onPause:
                    items.add("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
                    adapter.notifyDataSetChanged();
                    break;
                case onStop:
                    items.add("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
                    adapter.notifyDataSetChanged();
                    break;
                case onDestroy:
                    ToastUtils.show("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放.");
                    break;
                case onRestart:
                    items.add("8 onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    private void sendMessage(int what) {
        Message msg = mHandler.obtainMessage();
        msg.what = what;
        mHandler.sendMessageDelayed(msg,2000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("1 onCreate  声明周期的第一个方法.做一些初始化的动作,例如setContentView");
        sendMessage(onCreate);
    }




    @Override
    protected void onStart() {
        super.onStart();
        L.d("2 onStart 表示Activity正在被启动.Activity为理论可见(取决上层界面是否透明),但不是前台无法操作");
        sendMessage(onStart);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.d("onRestart 表示Activity重新启动.当界面从不可见变为可见时调用,场景Home键切换,从任务栈返回");
        sendMessage(onRestart);
    }


    @Override
    protected void onResume() {
        super.onResume();
        L.d("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
//        ToastUtils.show("3 onResume 表示Activity已经可见,并且为前台. 与onStart主要是前台后台,有无焦点的区别");
        sendMessage(onResume);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    sendMessage(onRunning);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        L.d("5 onPause 表示Activity正在停止. 做一些存储数据,停止动画操作.不能太耗时,这会影响新的Activity启动,onPause()必须先执行完,新Activity的onResume()才会执行.");
        sendMessage(onPause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d("6 onStop 表示Activity即将停止,可以做稍微重量级的回收工作,同样不能太耗时.");
        sendMessage(onStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.d("7 onDestroy 表示Activity即将被销毁. 可以做一些最终的资源释放.");
        sendMessage(onDestroy);
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

        lvIndex = findViewById(R.id.lvIndex);

        adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_list_item_1, items);
        lvIndex.setAdapter(adapter);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_life_cycle;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
