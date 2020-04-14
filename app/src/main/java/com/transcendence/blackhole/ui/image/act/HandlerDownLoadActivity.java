package com.transcendence.blackhole.ui.image.act;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.blackhole.R;
import com.transcendence.blackhole.base.activity.TitleBarActivity;
import com.transcendence.blackhole.utils.L;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author Joephone on 2020/3/27 0:11
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc  handler 实现线程间通讯，是因为共享了 looper 的内存，所以 handlerMessage( ) 运行在哪个线程由 looper 决定。
 *         而本章 new handler 默认拿的是主线程的 looper，因此运行在主线程。
 *        主线程创建时，消息队列和轮询器对象就会被创建;
 *         主线程中有一个消息轮询器looper，不断检测消息队列中是否有新消息，如果发现有新消息，自动调用此方法，注意此方法是在主线程中运行的;
 * @Edition 1.0
 * @EditionHistory
 */

public class HandlerDownLoadActivity extends TitleBarActivity{

    private TextView mTv;
    private ImageView mIv;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    L.d("下载成功");
                    Bitmap bm = (Bitmap) msg.obj;
                    mIv.setImageBitmap(bm);
                    break;
                case 2:
                    L.d("下载失败");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_handler_download;
    }

    @Override
    protected void init() {

        mIv = findViewById(R.id.ivImg);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new Thread(){
//                    @Override
//                    public void run() {
//                        syncDownLoad();
//                    }
//                }.start();

//                Thread r = new Thread(new SyncDownLoadRun());
//                r.start();
            }
        });
    }


    class SyncDownLoadRun implements Runnable {

        @Override
        public void run() {
            syncDownLoad();
        }
    }

    private void syncDownLoad() {
        try {
            //1.下载图片确定网址，将网址封装成url对象
            URL url = new URL("http://p1.so.qhmsg.com/t01e2b20000369dbd11.jpg");
            //2.获取客户端与服务器连接对象，此时还没有建立连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //3.对连接对象进行初始化

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            //4.发送请求，与服务器建立连接
            conn.connect();
            if(conn.getResponseCode()==200){
                //5.获取服务器响应头里的流，流中的数据就是请求端的数据
                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                Message msg = mHandler.obtainMessage();
                msg.obj = bitmap;
                msg.what = 1;
                mHandler.sendMessage(msg);
            } else {
                Message msg = mHandler.obtainMessage();
                msg.what = 2;
                mHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
