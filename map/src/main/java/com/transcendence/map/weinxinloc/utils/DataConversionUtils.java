package com.transcendence.map.weinxinloc.utils;

import android.text.TextUtils;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by XiaoFu on 2017-08-03 11:35.
 * 注释：数据转换类
 */

public class DataConversionUtils {

    public static PoiItem changeToPoiItem(RegeocodeResult data) {
        if (null != data) {
            try {
                String title = data.getRegeocodeAddress().getBuilding();
                if (TextUtils.isEmpty(title)) {
                    title = data.getRegeocodeAddress().getNeighborhood();
                }
                if (TextUtils.isEmpty(title)) {
                    title = data.getRegeocodeAddress().getTownship();
                }
                if (TextUtils.isEmpty(title)) {
                    title = "[位置]";
                }
                PoiItem poiItem = new PoiItem(data.getRegeocodeAddress().getBuilding(), data.getRegeocodeQuery().getPoint(), title, data.getRegeocodeAddress().getFormatAddress());

                poiItem.setAdCode(data.getRegeocodeAddress().getAdCode());
                poiItem.setAdName(data.getRegeocodeAddress().getDistrict());
                poiItem.setBusinessArea(data.getRegeocodeAddress().getBusinessAreas().get(0).getName());
                poiItem.setCityCode(data.getRegeocodeAddress().getCityCode());
                poiItem.setCityName(data.getRegeocodeAddress().getCity());
                poiItem.setProvinceName(data.getRegeocodeAddress().getProvince());

                return poiItem;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
