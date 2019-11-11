package com.transcendence.map.listener;

import com.amap.api.location.AMapLocation;

/**
 * @author Joephone on 2019/11/8 10:46
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public interface LocListener {
    /**
     *  定位成功
     *  @param aMapLocation
     */
    void onLocSuc(AMapLocation aMapLocation);
}
