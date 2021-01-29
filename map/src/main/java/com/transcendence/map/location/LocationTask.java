package com.transcendence.map.location;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.maps.LocationSource;
import com.transcendence.core.utils.L;
import com.transcendence.map.listener.LocListener;

/**
 * @author Joephone on 2019/11/7 17:56
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class LocationTask implements LocationSource,AMapLocationListener{

    private static LocationTask INSTANCE;
    private LocListener listener;
    private Context mContext;

    private AMapLocationClient mlocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    private OnLocationChangedListener mListener;


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
        mlocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        // 设置定位监听
        mlocationClient.setLocationListener(this);
        // 设置定位模式为高精度模式  Hight_Accuracy(高精度)、Battery_Saving(低功耗)、Device_Sensors(仅设备)
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置定位间隔时间
//        locationOption.setInterval(2000);
        // 设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 启动定位
     */
    public void startLocation()   {
        mlocationClient.startLocation();
    }

    /**
     * 停止定位,定位成功后调用
     */
    protected void stopLocation() {
        if (mlocationClient.isStarted()) {
            mlocationClient.stopLocation();
        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLoc) {
        L.d("获取位置");
        if (aMapLoc != null && aMapLoc.getErrorCode() == 0) {
            L.d("获取位置成功");
            updateDistrictLocation(aMapLoc);
        } else {
            L.d("获取位置失败");
//            tv.setText("获取位置失败");
//            Toast.makeText(this,"获取位置失败",Toast.LENGTH_SHORT).show();
        }
        stopLocation();
    }

    private void updateDistrictLocation(AMapLocation loc) {
        L.d("updateDistrictLocation: "+loc.getAddress());
        if (listener != null) {
            listener.onLocSuc(loc);
        } else {
            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
            showToastWithErrorInfo(loc.getErrorCode());
            Log.e("AmapError", "location Error, ErrCode:"
                    + loc.getErrorCode() + ", errInfo:"
                    + loc.getErrorInfo());

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

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(mContext);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }

    }

    @Override
    public void deactivate() {

    }


    private void showToastWithErrorInfo(int error) {
        String tips = "定位错误码：" + error;
        switch (error) {
            case 4:
                tips = "请检查设备网络是否通畅，检查通过接口设置的网络访问超时时间，建议采用默认的30秒。";
                break;
            case 7:
                tips = "请仔细检查key绑定的sha1值与apk签名sha1值是否对应。";
                break;
            case 12:
                tips = "请在设备的设置中开启app的定位权限。";
                break;
            case 18:
                tips = "建议手机关闭飞行模式，并打开WIFI开关";
                break;
            case 19:
                tips = "建议手机插上sim卡，打开WIFI开关";
                break;
        }
        Toast.makeText(mContext.getApplicationContext(), tips, Toast.LENGTH_LONG).show();
    }
}
