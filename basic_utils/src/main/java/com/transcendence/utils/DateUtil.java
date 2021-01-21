package com.transcendence.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Joephone on 2021/01/21 11:19
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class DateUtil {

    /**
     * 时间戳转换成日期格式字符串
     * @param timeStamp 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(long timeStamp) {
        return timeStamp2Date(timeStamp,"");
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(long seconds,String format) {
        if(seconds <0){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

}
