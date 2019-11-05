package com.transcendence.map.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.R;

/**
 * @author Joephone on 2019/10/15 14:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class AmapLocationActivity extends AppCompatActivity implements View.OnClickListener ,AMapLocationListener {

//    private MapView mapView;
//    private AMap aMap;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private AMapLocation mCurAMapLocation;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_amap_location);

        tv= (TextView) findViewById(R.id.tv);
//        mapView = (MapView) findViewById(R.id.map);
//        // 此方法必须重写
//        mapView.onCreate(savedInstanceState);
        initAmap();
        startLocation();


    }



    /**
     * 初始化AMap对象
     */
    /**
     * 初始化AMap对象
     */
    private void initAmap() {
//        if (aMap == null) {
//            aMap = mapView.getMap();
//        }

        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位监听
        locationClient.setLocationListener(this);
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置定位间隔时间
//        locationOption.setInterval(2000);
        // 设置定位参数
        locationClient.setLocationOption(locationOption);

    }

    protected void startLocation()   {

        // 启动定位
        locationClient.startLocation();
    }

    protected void stopLocation()   {
        if   (locationClient.isStarted()) {
            locationClient.stopLocation();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
//        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
//        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.basicmap:
//                aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
//                break;
//            case R.id.rsmap:
//                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
//                break;
        }

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        // TODO Auto-generated method   stub
        L.d("获取位置");
        if (aMapLocation != null   && aMapLocation.getErrorCode() == 0) {
            L.d("获取位置成功");
            updateDistrictLocation(aMapLocation);
        } else {
            tv.setText("获取位置失败");
//            Toast.makeText(this,"获取位置失败",Toast.LENGTH_SHORT).show();
        }
        stopLocation();
    }

    private void updateDistrictLocation(AMapLocation aMapLocation) {
        mCurAMapLocation =  aMapLocation;
//        Toast.makeText(TogetherMainActivity.this,aMapLocation.getAddress(),Toast.LENGTH_SHORT).show();
        tv.setText("详细定位地址"+aMapLocation.getAddress());
        //详细定位地址
        L.d("updateDistrictLocation: "+aMapLocation.getAddress());
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
}
