package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018

import android.content.Context;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteClosedHelper implements Interface {
    Context context;
    String DATABASENAME;
    String TABLENAME;
ArrayList<Fields > fields=new ArrayList<>();
    @Override
    public SqliteClosedHelper build() {
        return this;
    }

    @Override
    public SqliteClosedHelper build(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public SqliteClosedHelper build(Context c, String DBNAME) {
        build(c);
        this.DATABASENAME = DBNAME;
        return this;
    }

    @Override
    public SqliteClosedHelper build(Context c, String DBNAME, String TABLENAME) {
        build(c, DBNAME);
        this.TABLENAME = TABLENAME;
        return this;
    }

    @Override
    public SqliteClosedHelper setDatabaseName(String name) {
        this.DATABASENAME = name;
        return this;
    }

    @Override
    public SqliteClosedHelper setTable(String name) {
        this.TABLENAME = name;
        return this;
    }

    @Override
    public SqliteClosedHelper setTableFields(String name, int type) {
     fields.add(new Fields(TABLENAME,name,type))
        return this;
    }

    @Override
    public SqliteClosedHelper setTableFields(String name, int type, String defaultvalue) {
        setTableFields(name, type);
        defaultvalues.add(defaultvalue);
        return this;
    }

    @Override
    public SqliteClosedHelper setTableFields(String name, int type, int defaultvalue) {
        setTableFields(name, type);
        defaultvaluesint.add(defaultvalue);
        return this;
    }

    @Override
    public SqliteClosedHelper setTableFields(String name, int type, int defaultvalue, int parameters) {
        setTableFields(name, type);
        defaultvaluesint.add(defaultvalue);
        Parameters.add(parameters);
        return this;
    }

    @Override
    public SqliteClosedHelper setTableFields(String name, int type, String defaultvalue, int parameters) {
        setTableFields(name, type);
        defaultvalues.add(defaultvalue);
        Parameters.add(parameters);
        return this;
    }
}
