package com.transcendence.map.base.act;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.transcendence.map.R;

/**
 * @author Joephone on 2019/10/15 13:49
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BasicAmapActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mapView;
    private AMap aMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_amap_location);


        mapView = (MapView) findViewById(R.id.map);
        // 此方法必须重写
        mapView.onCreate(savedInstanceState);

        initAmap();
        startLocation();
    }

    private void startLocation() {
    }


    /**
     * 初始化AMap对象
     */
    private void initAmap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
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
}
