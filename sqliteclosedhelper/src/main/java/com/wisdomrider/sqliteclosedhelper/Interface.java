package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018


import android.database.Cursor;

import java.util.ArrayList;

interface Interface {
    SqliteClosedHelper setTable(String name);

    void query(String data);

    void close();

    SqliteClosedHelper setTableFields(String name, TYPE type);

    SqliteClosedHelper setTableFields(String name, TYPE type, PARAMETERS parameters);

    SqliteClosedHelper create();

    void renameTable(String oldname, String newname);

    void dropTable(String tablename);

    SqliteClosedHelper clearAll();

    boolean ifTableExist(String TABLENAME);

    boolean isFieldExist(String fieldname, Object value);

    Cursor getField(String key);

    SqliteClosedHelper insertFields(String data, Object value);

    void insert();

    SqliteClosedHelper clearAllFields();

    Cursor getAll();

    Cursor get(String query);

    SqliteClosedHelper delete(String where, Object Value);

    Cursor getWhere(String field, String value);

    Cursor getWhere(String field, int value);

    SqliteClosedHelper updateFields(String name, Object value);

    void update(String where, Object value);

    void setSharedPreferences(String name);

    <T> SqliteClosedHelper createTableFromClass(T table);

    <T> SqliteClosedHelper insertTableFromClass(T table);

    <T> SqliteClosedHelper updateTableFromClass(T table,T key);

    <T> ArrayList<T> getAll(T table);

    <T> ArrayList<T> getWhere(T table);


    TYPE getType(Class<?> type);
}