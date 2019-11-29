package com.transcendence.map.mobike.main.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.transcendence.blackhole.utils.L;
import com.transcendence.map.R;
import com.transcendence.map.listener.PoiSearchListener;
import com.transcendence.map.utils.MapUtil;
import com.transcendence.map.view.MapViewFragment;

/**
 * @author Joephone on 2019/11/8 15:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 高德地图父类
 * @Edition 1.0
 * @EditionHistory
 */

public class AmapFragmentActivity extends AppCompatActivity {

    protected AMap mMap;
    protected MapUtil mapUtil;
    protected MapViewFragment mFragment;
    protected PoiSearchListener poiListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void initMapViewFragment(){
        mFragment = (MapViewFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mMap = mFragment.getaMap();
        if (mMap != null) {
            L.d("mMap != null");
            mapUtil = MapUtil.getInstance();
        }
    }

}
