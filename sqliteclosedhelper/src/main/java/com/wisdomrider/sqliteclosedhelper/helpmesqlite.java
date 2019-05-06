package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
   Created By WisdomRider(Avishek Adhikari)

    Email : avishekzone@gmail.com

    Make Sure to Star Me On Github :
       https://github.com/wisdomrider/SqliteClosedHelper

     Credit Me SomeWhere In Your Project :)

     Thanks !!
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
