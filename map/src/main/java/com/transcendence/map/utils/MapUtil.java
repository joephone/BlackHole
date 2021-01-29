package com.transcendence.map.utils;

import android.content.Context;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.transcendence.core.utils.L;

/**
 * @author Joephone on 2019/10/17 16:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MapUtil{

    AmapUtil amapUtil;

    private static MapUtil instance;

    private MapUtil() {
    }

    public static MapUtil getInstance() {
        if (instance == null) {
            synchronized (MapUtil.class) {
                if (instance == null) {
                    instance = new MapUtil();
                }
            }
        }
        return instance;
    }

    public void initMap(AMap aMap, MapView aMapView, Context context){
        if (aMap != null) {
            amapUtil = new AmapUtil(aMap,aMapView,context);
        }
    }

    public void onMyLoc() {
        L.d("MapUtil onMyLoc");
        if (amapUtil != null) {
            amapUtil.onMyLoc();
        }else {
            L.d("amapUtil == null");
        }
    }

    public RegeocodeResult regeocodeResult(){
        return amapUtil.regeocodeResult();
    }



}
