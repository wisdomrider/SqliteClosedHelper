package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018

import android.content.Context;

interface  Interface{
    SqliteClosedHelper setTable(String name);
    SqliteClosedHelper query(String data);
    SqliteClosedHelper setTableFields(String name,TYPE type);
    SqliteClosedHelper setTableFields(String name,TYPE type,PARAMETERS parameters);
    void create();


}