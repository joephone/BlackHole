package com.transcendence.map.gpsloc;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

import com.transcendence.core.base.activity.TitleBarActivity;
import com.transcendence.map.R;
import com.transcendence.map.gpsloc.utils.GPSUtils;

/**
 * @Author Joephone on 2021/8/30 0030 上午 11:00
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class GpsLocActivity extends TitleBarActivity {

    private TextView tv;
    private Location location;
    private Handler handler = new Handler();
    private GPSUtils gpsUtils;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            location = gpsUtils.getLocation();//获取位置信息

            if (location != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateView(location);
                    }
                });
                handler.removeCallbacks(runnable);
            } else {
                handler.postDelayed(this, 1000);
            }
        }
    };


    /**
     * 实时更新文本内容
     *
     * @param location
     */
    private void updateView(Location location) {
        if (location != null) {
            tv.setText("定位成功\n\n设备位置信息\n\n经度：");
            tv.append(String.valueOf(location.getLongitude()));
            tv.append("\n纬度：");
            tv.append(String.valueOf(location.getLatitude()));
            tv.append("\n所在地址：");
            tv.append(String.valueOf(gpsUtils.getAddressStr()));
        } else {
            // 清空EditText对象
            tv.getEditableText().clear();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_gps;
    }

    @Override
    protected void init() {
        tv = (EditText) findViewById(R.id.tv);
        gpsUtils = new GPSUtils(mActivity);//初始化GPS
        handler.postDelayed(runnable, 0);
    }
}
