package com.transcendence.guard.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.transcendence.guard.domian.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Joephone on 2021/2/9 0009 下午 3:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ContactInfoParser {

    public static List<ContactInfo> getSystemContact(Context context) {
        ContentResolver resolver = context.getContentResolver();
        //1.查询raw contacts表， 把联系人的id取出来
        Uri uri = Uri.parse("content://com. android.contacts/raw_ contacts");
        Uri datauri = uri.parse("content://com.android.contacts/data");
        List<ContactInfo> infos = new ArrayList<>();

        Cursor cursor = resolver.query(uri, new String[]{"contact_id"},
                null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (id != null) {
                System.out.println("HÁ id: " + id);
                ContactInfo info = new ContactInfo();
                info.id = id;
                //2.根据联系人的id,查询data表，把这个id的数据取出来
                //系统API查询data表的时候，不是真正的查询data表，而是查询data表的视图
                Cursor dataCursor = resolver.query(datauri, new String[]{
                                "data1", "mimetype"}, "raw_contact_id=?",
                        new String[]{id}, null);
                while (dataCursor.moveToNext()) {
                    String datal = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                    if ("vnd.android.cursor.item name".equals(mimetype)) {
                        System.out.println(" 8=" + datal);
                        info.name = datal;
                    } else if ("vnd.android. cursor . item/phone v2"
                            .equals(mimetype)) {
                        System.out.println("tis- " + datal);
                        info.phone = datal;
                    }
                }
                infos.add(info);
                dataCursor.close();
            }
        }
        cursor.close();
        return infos;
    }
}
