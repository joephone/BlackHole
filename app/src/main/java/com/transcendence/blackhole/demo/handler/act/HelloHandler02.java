package com.transcendence.blackhole.demo.handler.act;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/12 11:51
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  使用handler sendMessage
 * @Edition 1.0
 * @EditionHistory
 */

public class HelloHandler02 extends TitleBarActivity {

    private TextView mTv;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mTv.setText(""+msg.obj);
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_base_handler_02;
    }

    @Override
    public void init() {
        setTitle("handler sendMessage");
        mTv = findViewById(R.id.tvContent);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    HandlerPerson person = new HandlerPerson();
                    person.name = "杨小盈"; person.age = 21;
                    Message msg = new Message();
                    msg.arg1 = 1;
                    msg.arg2 = 2;
                    msg.obj = person;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    class HandlerPerson {
        public String name;
        public int age;

        @Override
        public String toString() {
            return "name = "+name + "  age = " + age;
        }
    }


}
