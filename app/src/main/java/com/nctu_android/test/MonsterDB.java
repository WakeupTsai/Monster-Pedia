package com.nctu_android.test;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

//對positiontable的操作
public class MonsterDB {

    final static String POSITIONTABLE = "positiontable";

    //從POSITIONTABLE取出所有monster的id並放入list
    static ArrayList<String> getIDList(SQLiteDatabase db) {
        ArrayList<String> idlist = new ArrayList<String>();
        Cursor c = db.rawQuery("select Monster_id from " + POSITIONTABLE + ";", null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            int idIndex = c.getColumnIndex("Monster_id");
            String id = c.getString(idIndex);
            idlist.add(id);
            c.moveToNext();
        }
        return idlist;
    }

    //從POSITIONTABLE當中抓出指定id的monster的position
    static String getPosition(SQLiteDatabase db, String Monster_id) {
        Cursor c = db.rawQuery("select * from " +
                POSITIONTABLE + " where Monster_id='" + Monster_id +"';", null);
        c.moveToFirst();

        return c.getString(c.getColumnIndex("x"))+","+c.getString(c.getColumnIndex("y"));
    }

    //從POSITIONTABLE當中抓出指定id的monster的name
    static String getName(SQLiteDatabase db, String Monster_id) {
        Cursor c = db.rawQuery("select * from " +
                POSITIONTABLE + " where Monster_id='" + Monster_id +"';", null);
        c.moveToFirst();

        return c.getString(c.getColumnIndex("name"));
    }

    //從POSITIONTABLE當中抓出指定name的monster的id
    static String getId(SQLiteDatabase db, String Monster_name) {
        Cursor c = db.rawQuery("select Monster_id from " +
                POSITIONTABLE + " where name='" + Monster_name +"';", null);
        c.moveToFirst();

        return c.getString(c.getColumnIndex("Monster_id"));
    }

}
