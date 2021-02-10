package com.transcendence.guard.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acer on 2015/11/8.
 * 程序是否加锁的数据库
 */
public class AppLockHelper extends SQLiteOpenHelper {

    public AppLockHelper(Context context) {
        super(context, "app_lock", null, 1);
    }
    //数据库调用时创建用于创建id跟包名 锁的存在数据库里
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create  table app_lock(_id integer primary key autoincrement " +
                ", packageName varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
