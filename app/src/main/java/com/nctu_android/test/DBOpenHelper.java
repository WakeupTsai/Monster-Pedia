package com.nctu_android.test;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

    String POSITIONTABLE = "positiontable";
    String BAGTABLE = "bagtable";

    public DBOpenHelper(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //創建兩張table分別儲存所有monster的資訊和使用者擁有那些monster

        //POSITIONTABLE裡包括moster的id,monster的名字及其經緯度
        db.execSQL("create table positiontable (Monster_id, name, x, y);");
        //BAGTABLE裡包括使用者所擁有的monster的id
        db.execSQL("create table bagtable (Pet_id);");

        //初始化BAGTABLE
        ContentValues cv1 = new ContentValues();
        cv1.put("Pet_id", "a001");
        db.insert(BAGTABLE, null, cv1);

        cv1.put("Pet_id", "a004");
        db.insert(BAGTABLE, null, cv1);

        cv1.put("Pet_id", "a007");
        db.insert(BAGTABLE, null, cv1);


        //初始化POSITIONTABLE
        ContentValues cv = new ContentValues();
        cv.put("Monster_id", "a001");
        cv.put("name", "妙蛙種子");
        cv.put("x", "24.786574");
        cv.put("y", "120.996915");
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a002");
        cv.put("name", "妙蛙草");
        cv.put("x", "24.786006");
        cv.put("y", "120.995846");
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a003");
        cv.put("name", "妙蛙花");
        cv.put("x", "24.785397" );
        cv.put("y", "120.996146" );
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a004");
        cv.put("name", "小火龍");
        cv.put("x", "24.787015");
        cv.put("y", "120.997213");
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a005");
        cv.put("name", "火恐龍");
        cv.put("x", "24.787105");
        cv.put("y", "120.996113");
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a006");
        cv.put("name", "噴火龍");
        cv.put("x", 24.786098);
        cv.put("y", 120.998362);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a007");
        cv.put("name", "傑尼龜");
        cv.put("x", 24.786517);
        cv.put("y", 120.997858);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a008");
        cv.put("name", "卡咪龜");
        cv.put("x", 24.786956);
        cv.put("y", 120.998137);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a009");
        cv.put("name", "水箭龜");
        cv.put("x", 24.787930);
        cv.put("y", 120.997820);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a010");
        cv.put("name", "綠毛蟲");
        cv.put("x", 24.786304);
        cv.put("y", 120.999210);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a011");
        cv.put("name", "鐵甲蛹");
        cv.put("x", 24.786718);
        cv.put("y", 120.999607);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a012");
        cv.put("name", "巴大蝴");
        cv.put("x", 24.787229);
        cv.put("y", 120.999704);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a013");
        cv.put("name", "獨角蟲");
        cv.put("x", 24.787000);
        cv.put("y", 120.999135);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a014");
        cv.put("name", "鐵殼昆");
        cv.put("x", 24.787687);
        cv.put("y", 120.998609);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a015");
        cv.put("name", "大針蜂");
        cv.put("x", 24.788699);
        cv.put("y", 120.996018);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a016");
        cv.put("name", "波波");
        cv.put("x", 24.787746);
        cv.put("y", 120.996699);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a017");
        cv.put("name", "比比鳥");
        cv.put("x", 24.788491);
        cv.put("y", 120.996624);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a018");
        cv.put("name", "比雕");
        cv.put("x", 24.788447);
        cv.put("y", 120.998040);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a019");
        cv.put("name", "皮卡丘");
        cv.put("x", 24.789465);
        cv.put("y", 120.997697);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a020");
        cv.put("name", "雷丘");
        cv.put("x", 24.789363);
        cv.put("y", 120.996556);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a021");
        cv.put("name", "墨海馬");
        cv.put("x", 24.787537);
        cv.put("y", 121.001373);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a022");
        cv.put("name", "角金魚");
        cv.put("x", 24.788048);
        cv.put("y", 121.000772);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a023");
        cv.put("name", "海星星");
        cv.put("x", 24.788409);
        cv.put("y", 121.001137);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a024");
        cv.put("name", "鯉魚王");
        cv.put("x", 24.789999);
        cv.put("y", 120.996275);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a025");
        cv.put("name", "卡比獸");
        cv.put("x", 24.785421);
        cv.put("y", 120.999880);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a026");
        cv.put("name", "急凍鳥");
        cv.put("x", 24.785554);
        cv.put("y", 120.999204);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a027");
        cv.put("name", "閃電鳥");
        cv.put("x", 24.784598);
        cv.put("y", 120.998732);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a028");
        cv.put("name", "火焰鳥");
        cv.put("x", 24.785298);
        cv.put("y", 120.997855);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a029");
        cv.put("name", "迷你龍");
        cv.put("x", 24.785673);
        cv.put("y", 120.997233);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a030");
        cv.put("name", "哈克龍");
        cv.put("x", 24.785299);
        cv.put("y", 120.997225);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a031");
        cv.put("name", "快龍");
        cv.put("x", 24.788922);
        cv.put("y", 120.998872);
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a032");
        cv.put("name", "超夢");
        cv.put("x", 24.7839);//24 78630424.786367, 120.998589
        cv.put("y", 120.998);//120.997858 120,997820
        db.insert(POSITIONTABLE, null, cv);

        cv.put("Monster_id", "a033");
        cv.put("name", "夢幻");
        cv.put("x", 24.784731);
        cv.put("y", 120.997381);
        db.insert(POSITIONTABLE, null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

}

