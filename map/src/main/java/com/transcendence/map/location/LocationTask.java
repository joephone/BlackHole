package com.transcendence.map.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.listener.LocListener;

/**
 * @author Joephone on 2019/11/7 17:56
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LocationTask implements AMapLocationListener{

    private static LocationTask INSTANCE;
    private LocListener listener;
    private Context mContext;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;


    public LocationTask(Context context) {
        mContext = context;
        initAmap(context);
        startLocation();
    }

    public static LocationTask getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocationTask(context);
        }
        return INSTANCE;
    }

    private void initAmap(Context context) {
        locationClient = new AMapLocationClient(context);
        locationOption = new AMapLocationClientOption();
        // 设置定位监听
        locationClient.setLocationListener(this);
        // 设置定位模式为高精度模式  Hight_Accuracy(高精度)、Battery_Saving(低功耗)、Device_Sensors(仅设备)
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置定位间隔时间
//        locationOption.setInterval(2000);
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
    }

    /**
     * 启动定位
     */
    public void startLocation()   {
        locationClient.startLocation();
    }

    /**
     * 停止定位,定位成功后调用
     */
    protected void stopLocation() {
        if (locationClient.isStarted()) {
            locationClient.stopLocation();
        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        L.d("获取位置");
        if (aMapLocation != null   && aMapLocation.getErrorCode() == 0) {
            L.d("获取位置成功");
            updateDistrictLocation(aMapLocation);
        } else {
            L.d("获取位置失败");
//            tv.setText("获取位置失败");
//            Toast.makeText(this,"获取位置失败",Toast.LENGTH_SHORT).show();
        }
        stopLocation();
    }

    private void updateDistrictLocation(AMapLocation aMapLocation) {
        L.d("updateDistrictLocation: "+aMapLocation.getAddress());
        if (listener != null) {
            listener.onLocSuc(aMapLocation);
        }
        //          location.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
        //          location.getLatitude();//获取纬度
        //          location.getLongitude();//获取经度
        //          location.getAccuracy();//获取精度信息
        //          @SuppressLint("SimpleDateFormat")
        //        SimpleDateFormat df = new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        Date date = new   Date(location.getTime());
        //          df.format(date);//定位时间
        //          location.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
        //          location.getCountry();//国家信息
        //          location.getProvince();//省信息
        //          location.getCity();//城市信息
        //          location.getDistrict();//城区信息
        //          location.getStreet();//街道信息
        //          location.getStreetNum();//街道门牌号信息
        //          location.getCityCode();//城市编码
        //          location.getAdCode();//地区编码
    }

    public void setListener(LocListener listener) {
        this.listener = listener;
    }
}
