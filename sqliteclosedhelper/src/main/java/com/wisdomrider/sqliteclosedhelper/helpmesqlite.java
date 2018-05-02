package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by WisdomRider (Avishek Adhikari) on 4/12/2018.
 */

public class helpmesqlite extends SQLiteOpenHelper {

    public helpmesqlite(Context context,String name) {
        super(context,name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        onCreate(database);
    }
    public SQLiteDatabase getDatabase(){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db;
    }





}
