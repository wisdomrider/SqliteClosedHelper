package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018

import android.content.Context;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteClosedHelper implements Interface {
    private Context context;
    private String DATABASENAME;
    private String TABLENAME;
    private ArrayList<Fields> fields = new ArrayList<>();
    helpmesqlite Sqlite;
    public SqliteClosedHelper(Context context, String dbname) {
        this.context = context;
        this.DATABASENAME = dbname;
        Sqlite=new helpmesqlite(context,dbname);
    }

    @Override
    public SqliteClosedHelper setTable(String name) {
        this.TABLENAME = name;
        return this;
    }

    @Override
    public SqliteClosedHelper query(String data) {
        Sqlite.query(data);
        return this;
    }


    @Override
    public SqliteClosedHelper setTableFields(String name, TYPE type) {
        fields.add(new Fields(name, type, Wisdom.NONE()));
        return this;
    }


    @Override
    public SqliteClosedHelper setTableFields(String name, TYPE type, PARAMETERS parameters) {
        fields.add(new Fields(name, type, parameters));
        return this;
    }

    @Override
    public void create() {
        String var_name="";
        for(int a=0;a<fields.size();a++){
            Fields field=fields.get(a);
            var_name=var_name+field.getFieldname()+" "+field.getType()+" "+field.getParameters()+",";
        }
        var_name=var_name.substring(0,var_name.length()-1);
        query("create table if not exists "+TABLENAME+ " ("+var_name+")");
        Toast.makeText(context, "create table "+TABLENAME+ " ("+var_name+")", Toast.LENGTH_SHORT).show();
    }


}
