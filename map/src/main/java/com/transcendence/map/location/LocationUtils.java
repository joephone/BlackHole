package com.transcendence.map.location;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.transcendence.blackhole.base.app.LibApplication;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.listener.LocListener;

/**
 * @author Joephone on 2020/5/27 01:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LocationUtils implements AMapLocationListener {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private AMapLocation location;
    private AMapLocationListener mAMapLocationListener;

    private LocListener listener;
    private static LocationUtils INSTANCE;

    private LocationUtils(){}

    public static LocationUtils getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new LocationUtils();
        }
        return INSTANCE;
    }


    /**
     * 初始化定位
     */
    private void initLocation() {
        if (null == locationClient) {
            //初始化client
            locationClient = new AMapLocationClient(LibApplication.getAppContext());
            //设置定位参数
            locationClient.setLocationOption(getDefaultOption());
            // 设置定位监听
            locationClient.setLocationListener(mAMapLocationListener);
        }
    }


    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setMockEnable(true);//如果您希望位置被模拟，请通过setMockEnable(true);方法开启允许位置模拟
        return mOption;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        initLocation();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        if (null != locationClient) {
            locationClient.stopLocation();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (loc != null && loc.getErrorCode() == 0) {
            L.d("获取位置成功");
            updateDistrictLocation(loc);
        } else {
            L.d("获取位置失败");
//            tv.setText("获取位置失败");
//            Toast.makeText(this,"获取位置失败",Toast.LENGTH_SHORT).show();
        }
        stopLocation();
    }

    private void updateDistrictLocation(AMapLocation loc) {
        if (listener != null) {
            listener.onLocSuc(loc);
        } else {
            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//            showToastWithErrorInfo(loc.getErrorCode());
//            Log.e("AmapError", "location Error, ErrCode:"
//                    + loc.getErrorCode() + ", errInfo:"
//                    + loc.getErrorInfo());

        }
    }

    public void setListener(LocListener listener) {
        this.listener = listener;
    }
}
