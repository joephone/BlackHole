package com.transcendence.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author joephone
 * @date 2022/12/11
 * @desc
 */
public class DateUtils {

    /**
     *  计算天数
     * @param time 两时间之差
     * @return
     */
    public static long day(long time){
        return time/1000 / (60 * 60 * 24);
    }

    public static long second(long time){
        return time/1000;
    }

    static DateUtils instance;

    private SimpleDateFormat dateFormater1 = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat dateFormaterTimer = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final  SimpleDateFormat dateFormaterTimer1 = new SimpleDateFormat(
            "yyyy-MM-dd");
    private final  SimpleDateFormat dateFormaterTimer3 = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm");
    private final  SimpleDateFormat dateFormaterTimer4 = new SimpleDateFormat(
            "dd/MM/yyyy HH:mm");
    private final  SimpleDateFormat dateFormaterTimer5 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    private final  SimpleDateFormat dateFormaterTimer2 = new SimpleDateFormat(
            "HH:mm");
    private final  SimpleDateFormat dateFormaterTimer7 = new SimpleDateFormat(
            "yyyy/MM/dd");
    private final  SimpleDateFormat dateFormaterTimer8 = new SimpleDateFormat(
            "yyyy.MM.dd HH:mm");
    private final  SimpleDateFormat dateFormaterTimer9 = new SimpleDateFormat(
            "MM.dd");

    private final  SimpleDateFormat dateFormaterTimer6 = new SimpleDateFormat("yyyy年MM月");

    private final  SimpleDateFormat dateFormaterTimer10 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    private final  SimpleDateFormat dateFormaterTimer11 = new SimpleDateFormat(
            "MM-dd HH:mm");
    private final  SimpleDateFormat dateFormaterTimer12 = new SimpleDateFormat(
            "MM/dd");

    /*
     * 单例模式中获取唯一的Utli实例
     */
    public static DateUtils getInstance() {
        if (null == instance) {
            instance = new DateUtils();
        }
        return instance;
    }

    public String formatLong2Date(long time) {
        try {
            String date = dateFormater1.format(new Date(time));
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "";
    }

}
