package com.transcendence.serialport.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.hjq.toast.ToastUtils;
import com.transcendence.core.utils.L;
import com.transcendence.network.cangahi.BaseObserver;
import com.transcendence.network.cangahi.BaseRequest;
import com.transcendence.network.cangahi.BaseResponse;
import com.transcendence.network.service.ParamAis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author Joephone on 2021/9/26 0026 下午 2:41
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class TimerService extends Service {

    private Context mContext;
    private boolean pushthread = false;
    public TimerService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.d("onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d("onStartCommand");
        if (intent.getStringExtra("flags").equals("3")) {
            //判断当系统版本大于20，即超过Android5.0时，我们采用线程循环的方式请求。
            //当小于5.0时的系统则采用定时唤醒服务的方式执行循环
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion > 20) {
                getPushThread();
            } else {
                fetchAis();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //循环请求的线程
    public void getPushThread() {
        pushthread = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pushthread) {
                    try {
                        Thread.sleep(5000);
                        fetchAis();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static String mText = "";

    //请求网络获取数据
    public void fetchAis(){
        List<String> list = new ArrayList<>();
        if(!TextUtils.isEmpty(mText)){
            list.add("mText");
            Map<String,Object> map = ParamAis.getInstance().aisFetch(list);

            BaseRequest.getInstance().getApiService().aisFetch(map).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<RequestBody>(this) {
                @Override
                public void onSuccess(BaseResponse<RequestBody> t) {
                    //成功回调方法,能够直接在此更新ui,AndroidSchedulers.mainThread()表示切换到主线程
                    L.d("success");
                    Looper.prepare();
                    ToastUtils.show("数据发送成功");
                    Looper.loop();
                }

                @Override
                public void onCodeError(BaseResponse baseReponse) {
                    //失败回调方法,能够直接在此更新ui,AndroidSchedulers.mainThread()表示切换到主线程
                    L.d("onCodeError");
                }

                @Override
                public void onFailure(Throwable e, boolean netWork) throws Exception {
                    L.d("onFailure");
                }
            });
        } else {
            Looper.prepare();
            ToastUtils.show("无效数据");
            Looper.loop();
            L.d("无效数据");
        }



    }

    @Override
    public void onDestroy() {
        pushthread = false;
        L.d( "onDestroy");
        super.onDestroy();
    }



    //启动服务和定时器
    public static void getConnet(Context mContext) {
        try {
            Intent intent = new Intent(mContext, TimerService.class);
            intent.putExtra("flags", "3");
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion > 20) {
                //一般的启动服务的方式
                mContext.startService(intent);
            } else {
                //定时唤醒服务的启动方式
                PendingIntent pIntent = PendingIntent.getService(mContext, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) mContext
                        .getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis(), 3000, pIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //停止由AlarmManager启动的循环
    public static void stop(Context mContext) {
        Intent intent = new Intent(mContext, TimerService.class);
        PendingIntent pIntent = PendingIntent.getService(mContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mContext
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pIntent);
    }

}
