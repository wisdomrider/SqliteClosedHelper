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
    public Context context;
    public String TEXT="TEXT";
    public String BLOB="BLOB";
    public String INTEGER="INTEGER";
    public String NOT_NULL="NOT NULL";
    public String DEFAULT="DEFAULT";
    public String DATETIME="DATETIME";
    public  String table;
    ArrayList var_name=new ArrayList();
    ArrayList type=new ArrayList();
    ArrayList params=new ArrayList();

    public helpmesqlite(Context context,String name) {
        super(context,name, null, 1);
        this.context=context;
    }
    public void setTable(String table){
        this.table=table;
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("create table "+table+" (_ID INTEGER PRIMARY KEY AUTOINCREMENT,DATA TEXT,ID TEXT,READ INTEGER,time DATETIME)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
//        database.execSQL("drop table if exists "+table);
        onCreate(database);
    }
    public String date(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public boolean insert(String data,String id,int read,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("DATA",data);
        values.put("ID",id);
        if(time.isEmpty()){
            values.put("time",date());
        }
        else{
            values.put("time",time);
        }

        long result=  db.insert(table,null,values);
        if(result==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public String lastVaue(String token){
        String id=Integer.toString(getIdbyToken(token));

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+table+" order  by datetime(time) DESC LIMIT 1",null);
        String data="";
        while(res.moveToNext()){
            data=res.getString(2);
        }

        return  data;
    }


    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+table+" order  by datetime(time) DESC",null);
        return res;
    }
    public boolean isFieldExist( String fieldName)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        if(db==null) return  false;
        Cursor res=db.rawQuery("select ID from "+table,null);
        if(res.getCount()==0){
            return false;
        }
        else{
            while(res.moveToNext()){
                if(fieldName.equals(res.getString(0))) return true;
            }
        }


        return false;
    }


    public int getIdbyToken(String token) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table, null);
        if (res.getCount() == 0) {
            return 0;
        } else {
            while (res.moveToNext()) {
                if (token.equals(res.getString(3))) return res.getInt(0);
            }
            return 0;
        }
    }
    public boolean update(String message,String token){
        String id=Integer.toString(getIdbyToken(token));
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("MESSAGE",message);
        values.put("time",date());
        db.update(table,values, "ID = ?", new String[] { id });
        return true;
    }

}
