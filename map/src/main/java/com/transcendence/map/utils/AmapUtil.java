package com.transcendence.map.utils;

import android.content.Context;

import com.amap.api.maps2d.AMap;
import com.transcendence.blackhole.utils.L;

/**
 * @author Joephone on 2019/11/7 17:29
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class AmapUtil extends AmapHelper {


    public AmapUtil(AMap paramAmap, Context context){
        L.d("子类构造");
        this.aMap = paramAmap;
        this.mContext = context;
        initHelper();
    }


    public static void init(AMap aMap, Context context) {
        new AmapUtil(aMap,context);
    }



}
