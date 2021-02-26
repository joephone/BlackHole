package com.transcendence.guard.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.transcendence.guard.domian.BlackNumberInfo;
import com.transcendence.guard.helper.BlackNumberHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/2/10 0010 下午 1:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class BlackNumberDao {

    private BlackNumberHelper blackNumberHelper;

    public BlackNumberDao(Context context) {
        blackNumberHelper = new BlackNumberHelper(context);
    }

    /**
     * 查询所有号码
     */
    public List<BlackNumberInfo> getAll() {
        List<BlackNumberInfo> numberList = new ArrayList<>();
        SQLiteDatabase db = blackNumberHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select number,mode from blackNumber order by bid desc", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                BlackNumberInfo b = new BlackNumberInfo();
                b.setBlackName(cursor.getString(0));
                b.setMode(cursor.getInt(1));
                numberList.add(b);
            }
            cursor.close();
        }
        db.close();
        return numberList;
    }

    /**
     * @param offset    从第几个开始加载
     * @param maxNumber 最大加载数量
     */
    public List<BlackNumberInfo> getPageAll(int offset, int maxNumber) {
        List<BlackNumberInfo> numberList = new ArrayList<>();
        SQLiteDatabase db = blackNumberHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select number,mode from blackNumber" +
                        " order by bid desc limit ? offset ?",
                new String[]{String.valueOf(maxNumber), String.valueOf(offset)});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                BlackNumberInfo b = new BlackNumberInfo();
                b.setBlackName(cursor.getString(0));
                b.setMode(cursor.getInt(1));
                numberList.add(b);
            }
            cursor.close();
        }
        db.close();
        SystemClock.sleep(30);
        return numberList;
    }

    /**
     * 增加方法
     */
    public boolean add(BlackNumberInfo number) {
        SQLiteDatabase db = blackNumberHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number.blackNumber);
        contentValues.put("mode", number.mode);
        long rowId = db.insert("blackNumber", null, contentValues);
        db.close();
        if(rowId==-1){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 修改方法
     * 修改拦截模式
     */
    public void updateNumber(String number, String mode) {
        SQLiteDatabase db = blackNumberHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mode", mode);
        String whereClause = "number=?";
        String[] whereArgs = new String[]{number};
        db.update("blackNumber", contentValues, whereClause, whereArgs);
        db.close();
    }

    /**
     * 删除方法
     */
    public boolean delete(BlackNumberInfo info) {
        SQLiteDatabase db = blackNumberHelper.getWritableDatabase();
        int rownumber = db.delete("blackNumber", "number=?", new String[]{info.blackNumber});
        db.close();
        if(rownumber == 0){
            return false;    //delete data fail
        }else {
            return true;
        }
    }

    /**
     * @param number
     * @return 没查到返回null 查到返回mode
     */
    public String findMode(String number) {
        SQLiteDatabase db = blackNumberHelper.getReadableDatabase();
        String result = " ";
        Cursor cursor =
                db.rawQuery("select mode from blackNumber where number=?", new String[]{number});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                result = cursor.getString(0);
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    /**
     * @return 数据库的条目数
     */
    public int getCount() {
        int count = 0;
        SQLiteDatabase db = blackNumberHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from blackNumber", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        db.close();
        return count;
    }

    /**
     * 判断号码是否黑名单存在
     * @param number
     * @return
     */
    public boolean isNumberExist(String number){
        SQLiteDatabase db = blackNumberHelper.getReadableDatabase();
        Cursor cursor = db.query("blackNumber",null,"number=?",
        new String[]{number},null,null,null);
        if(cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

}
