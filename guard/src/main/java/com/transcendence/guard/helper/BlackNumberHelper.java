package com.transcendence.guard.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acer on 2015/11/8.
 * 黑名单数据库
 */
public class BlackNumberHelper extends SQLiteOpenHelper {

    public BlackNumberHelper(Context context) {
        super(context, "blackNumber", null, 1);
    }
    //数据库调用时创建用于创建变805352431615
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create  table blackNumber (bid integer primary key autoincrement " +
                ", number varchar(20) , name varchar(255) ,mode integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
