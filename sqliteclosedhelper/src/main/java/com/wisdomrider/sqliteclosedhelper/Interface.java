package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018


import android.database.Cursor;

interface  Interface{
    SqliteClosedHelper setTable(String name);
    void query(String data);
    SqliteClosedHelper setTableFields(String name,TYPE type);
    SqliteClosedHelper setTableFields(String name,TYPE type,PARAMETERS parameters);
    SqliteClosedHelper create();
    void renameTable(String oldname,String newname);
    void dropTable(String tablename);
    void clearAll();
    boolean ifTableExist(String TABLENAME);
    boolean isFieldExist(String fieldname,Object value);
    SqliteClosedHelper insertFields(String data,Object value);
    void insert();
    Cursor getAll();
    Cursor get(String query);
    Cursor getWhere(String field,String value);
    Cursor getWhere(String field,int value);
    SqliteClosedHelper updateFields(String  name,Object value);
    void update(String where,Object value);

}