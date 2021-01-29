package com.transcendence.blackhole.demo.handler.act;

import android.os.Handler;
import android.widget.ImageView;

import com.transcendence.blackhole.R;
import com.transcendence.core.base.activity.TitleBarActivity;

/**
 * @author Joephone on 2019/6/12 10:57
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  使用handler 定时切换背景
 * @Edition 1.0
 * @EditionHistory
 */

public class HelloHandler01 extends TitleBarActivity {

    private ImageView mIv;
    private Handler mHandler = new Handler();
    private IvRunnable mRunnable = new IvRunnable();
    private int index;
    private int[] mImageIds = new int[]{R.mipmap.beauty02,R.mipmap.beauty06,R.mipmap.beauty04,R.mipmap.beauty01};

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_handler_01;
    }

    @Override
    public void init() {
        setTitle("定时切换背景");
        mIv = findViewById(R.id.ivImg);
        mHandler.postDelayed(mRunnable,0);   //0秒进行切换
    }

    class IvRunnable implements Runnable {

        @Override
        public void run() {
            index++;
            index = index%(mImageIds.length);
            mIv.setImageResource(mImageIds[index]);
            mHandler.postDelayed(mRunnable,2000);   //2秒切换
        }
    }
}
