package com.nctu_android.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

//對bagtable的操作
public class BagDB {

    final static String BAGTABLE = "bagtable";

    //從BAGTABLE取出所有使用者所收集的monster的id
    static ArrayList<String> getIDList(SQLiteDatabase db) {
        ArrayList<String> idlist = new ArrayList<String>();
        Cursor c = db.rawQuery("select Pet_id from " + BAGTABLE + ";", null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            int idIndex = c.getColumnIndex("Pet_id");
            String id = c.getString(idIndex);
            idlist.add(id);
            c.moveToNext();
        }
        return idlist;
    }

    //將使用者新抓到的BAGTABLE新增到table中
    static void addMonster(SQLiteDatabase db, String MonsterId) {

        ContentValues cv1 = new ContentValues();
        cv1.put("Pet_id", MonsterId);
        db.insert(BAGTABLE, null, cv1);
    }
}
