package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018

import android.content.Context;

interface  Interface{
    SqliteClosedHelper build();
    SqliteClosedHelper build(Context context);
    SqliteClosedHelper build(Context c,String DBNAME);
    SqliteClosedHelper build(Context c,String DBNAME,String TABLENAME);
    SqliteClosedHelper setDatabaseName(String name);
    SqliteClosedHelper setTable(String name);
    SqliteClosedHelper setTableFields(String name,int type);
    SqliteClosedHelper setTableFields(String name,int type,String defaultvalue);
    SqliteClosedHelper setTableFields(String name,int type,int defaultvalue);
    SqliteClosedHelper setTableFields(String name,int type,int defaultvalue,int parameters);
    SqliteClosedHelper setTableFields(String name,int type,String defaultvalue,int parameters);



}