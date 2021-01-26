package com.transcendence.wan.utils;

import com.transcendence.wan.base.app.WanApp;

/**
 * @Author Joephone on 2021/1/26 0026 上午 11:37
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class StringUtils {

    /**
     * 根据id 得到字符串
     */
    public static String getString(int id) {
        try {
            return WanApp.getAppContext().getApplicationContext().getString(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

}
