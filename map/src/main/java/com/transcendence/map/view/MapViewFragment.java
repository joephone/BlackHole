package com.transcendence.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.transcendence.core.utils.L;
import com.transcendence.map.R;
import com.transcendence.map.utils.MapUtil;

/**
 * @author Joephone on 2019/11/7 16:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MapViewFragment extends Fragment {

    private int mapService=0;
    private MapUtil mapUtil;
    /**
     * g地图view
     */
    private View viewMap;

    private MapView aMapView;
    private AMap aMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d("MapViewFragment onCreateView");
        mapUtil = MapUtil.getInstance();
        if(mapService==0){
            if (viewMap == null) {
                viewMap = inflater.inflate(R.layout.activity_amap, null);
                aMapView = viewMap.findViewById(R.id.aMapView);
                if (aMap == null) {
                    aMap = aMapView.getMap();
                    mapUtil.initMap(aMap,aMapView,getActivity());
                }
                // 此方法必须重写
                aMapView.onCreate(savedInstanceState);
            }
            return viewMap;
        }else {
            return viewMap;
        }
    }


    /**
     * 在自定义控件内得到地图对象
     * @return
     */
    public AMap getaMap() {
        return aMap;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        if (aMapView == null) {
            aMapView.onResume();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        if (aMapView == null) {
            aMapView.onPause();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (aMapView == null) {
            aMapView.onSaveInstanceState(outState);
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (aMapView == null) {
            aMapView.onDestroy();
        }
    }
}
