package com.transcendence.map.utils;

import android.content.Context;

import com.amap.api.maps2d.AMap;

/**
 * @author Joephone on 2019/10/17 16:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class MapUtil{


    public void initMap(AMap aMap,Context context){
        if (aMap != null) {
            AmapUtil.init(aMap,context);
        }
    }

}
