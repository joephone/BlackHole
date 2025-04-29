package com.transcendence.core.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author joephone
 * @date 2023/9/12
 * @desc
 */
public class DateUtils {

    // 定义日期时间格式
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final SimpleDateFormat dateFormaterTimer1 = new SimpleDateFormat(
            "yyyy-MM-dd");

    private final SimpleDateFormat dateFormaterTimer2 = new SimpleDateFormat(
            "HH:mm");

    private final SimpleDateFormat dateFormaterTimer3 = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm");
    private final SimpleDateFormat dateFormaterTimer4 = new SimpleDateFormat(
            "dd/MM/yyyy HH:mm");
    private final SimpleDateFormat dateFormaterTimer5 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");

    private final SimpleDateFormat dateFormaterTimer6 = new SimpleDateFormat("yyyy年MM月");

    private final SimpleDateFormat dateFormaterTimer7 = new SimpleDateFormat(
            "yyyy/MM/dd");

    private final SimpleDateFormat dateFormaterTimer8 = new SimpleDateFormat(
            "yyyy.MM.dd HH:mm");

    private final SimpleDateFormat dateFormaterTimer9 = new SimpleDateFormat(
            "MM.dd");

    private final SimpleDateFormat dateFormaterTimer10 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    private final SimpleDateFormat dateFormaterTimer11 = new SimpleDateFormat(
            "MM-dd HH:mm");
    private final SimpleDateFormat dateFormaterTimer12 = new SimpleDateFormat(
            "更新于:MM/dd HH:mm");

    private final SimpleDateFormat dateFormaterTimer13 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm:ss");

    static DateUtils instance;
    /*
     * 单例模式中获取唯一的Utli实例
     */
    public static DateUtils getInstance() {
        if (null == instance) {
            instance = new DateUtils();
        }
        return instance;
    }


    public String formatDate2String(String str) {
        try {
            String date = formatter.format(new Date(Long.parseLong(str)));
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "";
    }

    public String formatDate12String(String str) {
        try {
            String date = dateFormaterTimer12.format(new Date(Long.parseLong(str)));
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "";
    }

    public String formatDate13String(String str) {
        try {
            String date = dateFormaterTimer13.format(new Date(Long.parseLong(str)));
            return date;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "";
    }
}
