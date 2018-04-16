package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SqliteClosedHelper implements Interface {
    private Context context;
    private String DATABASENAME;
    private String TABLENAME;
    private SQLiteDatabase database;
    private ArrayList<Fields> fields = new ArrayList<>();
    private ArrayList<Insert> insert = new ArrayList<>();
    private ArrayList<Insert> update = new ArrayList<>();
    private helpmesqlite Sqlite;

    public SqliteClosedHelper(Context context, String dbname) {
        this.context = context;
        this.DATABASENAME = dbname;
        Sqlite = new helpmesqlite(context, dbname);
        database = Sqlite.getDatabase();

    }


    @Override
    public SqliteClosedHelper setTable(String name) {
        this.TABLENAME = name;
        fields.clear();
        return this;
    }


    @Override
    public void query(String data) {
        database.execSQL(data);
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
    public SqliteClosedHelper create() {
        String var_name = "";
        for (int a = 0; a < fields.size(); a++) {
            Fields field = fields.get(a);
            var_name = var_name + field.getFieldname() + " " + field.getType() + " " + field.getParameters() + ",";
        }
        var_name = var_name.substring(0, var_name.length() - 1);
        if (!var_name.toLowerCase().contains("text primary")) {
            query("create table if not exists " + TABLENAME + " (" + var_name + ")");
        } else {
            throw new Error("Text cannot be primary !");
        }

        fields.clear();
        return this;
    }



    @Override
    public void renameTable(String oldname, String newname) {
        query("ALTER TABLE " + oldname + " RENAME TO " + newname + ";");
    }

    @Override
    public void dropTable(String tablename) {
        query("DROP TABLE " + tablename);
    }

    @Override
    public SqliteClosedHelper updateFields(String name, Object value) {
        update.add(new Insert(name, value));
        return this;
    }

    @Override
    public void update(String where, Object value) {
        ContentValues contentValues = new ContentValues();
        for (int y = 0; y < update.size(); y++) {
            Insert data = update.get(y);
            if (data.getValue() instanceof Integer) {
                contentValues.put(data.getName(), (Integer) data.getValue());
            } else if (data.getValue() instanceof String) {
                contentValues.put(data.getName(), data.getValue().toString());
            } else {
                throw new Error("Object not recognized must be int or string ");
            }
        }
        String valuee;
        if (value instanceof Integer) {
            valuee = Integer.toString((Integer) value);
        } else {
            valuee = value.toString();
        }
        database.update(TABLENAME, contentValues, where + " = ?", new String[]{valuee});
        fields.clear();

    }


    @Override
    public void clearAll() {
        fields.clear();
    }

    @Override
    public boolean ifTableExist(String table1) {
            try {
                database.rawQuery("SELECT * FROM " + table1,null);
                return true;
            } catch (SQLException e) {
                return false;
            }

     }

    @Override
    public boolean isFieldExist(String fieldname, Object value) {
        if(database==null) return  false;
        Cursor res=database.rawQuery("select "+fieldname+" from "+TABLENAME,null);
        if(res.getCount()==0){
            return false;
        }
        else{
            while(res.moveToNext()){
                int data=check(value);
                if(data==1){
                    if((Integer)value==res.getInt(0)) return true;
                }
                else if(data==2){
                    if(value.toString().equals(res.getString(0))) return true;

                }

            }
        }
        return false;
    }

    @Override
    public SqliteClosedHelper insertFields(String data, Object value) {
        insert.add(new Insert(data, value));
        return this;
    }



    public int check(Object data){
        if (data instanceof Integer) {
           return 1;
        } else if (data instanceof String) {
            return  2;
        } else {
            throw new Error("Object not recognized must be int or string ");
        }
    }


    @Override
    public void insert() {
        ContentValues contentValues = new ContentValues();
        for (int y = 0; y < insert.size(); y++) {
            Insert data = insert.get(y);
            if (data.getValue() instanceof Integer) {
                contentValues.put(data.getName(), (Integer) data.getValue());
            } else if (data.getValue() instanceof String) {
                contentValues.put(data.getName(), data.getValue().toString());
            } else {
                throw new Error("Object not recognized must be int or string ");
            }
        }

        database.insertOrThrow(TABLENAME, null, contentValues);

    }


    @Override
    public Cursor getAll() {
        Cursor res = database.rawQuery("select * from " + TABLENAME, null);
        return res;
    }


    @Override
    public Cursor getWhere(String tablefield, String value) {
        Cursor res = database.rawQuery("select * from " + TABLENAME + " where " + tablefield + "='" + value + "';", null);
        return res;
    }

    @Override
    public Cursor getWhere(String tablefield, int value) {
        Cursor res = database.rawQuery("select * from " + TABLENAME + " where " + tablefield + "=" + value + ";", null);
        return res;
    }

    @Override
    public Cursor get(String query) {
        Cursor res = database.rawQuery("select * from " + TABLENAME + " " + query, null);
        return res;
    }


}
