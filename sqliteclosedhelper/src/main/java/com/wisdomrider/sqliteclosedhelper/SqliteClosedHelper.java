package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/12/2018


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SqliteClosedHelper implements Interface {
    private Context context;
    private String DATABASENAME;
    private String TABLENAME;
    private SQLiteDatabase database;
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    private ArrayList<Fields> fields = new ArrayList<>();
    private ArrayList<Insert> insert = new ArrayList<>();
    private ArrayList<Insert> update = new ArrayList<>();
    private helpmesqlite Sqlite;

    public SqliteClosedHelper(Context context, String dbname) {
        this.context = context;
        this.DATABASENAME = dbname;
        Sqlite = new helpmesqlite(context, dbname);
        sharedPreferences = context.getSharedPreferences("SqliteClosedHelper_data", 0);
        sharedPreferencesEditor = sharedPreferences.edit();
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
    public void close() {
        database.close();
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
            } else if (data.getValue() instanceof Long) {
                contentValues.put(data.getName(), (long) data.getValue());
            } else if (data.getValue() instanceof Float) {
                contentValues.put(data.getName(), (float) data.getValue());
            } else {
                throw new Error("Object not recognized must be long, int or string ");
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
    public void setSharedPreferences(String name) {
        sharedPreferences = context.getSharedPreferences(name, 0);
        sharedPreferencesEditor = sharedPreferences.edit();
    }


    @Override
    public SqliteClosedHelper clearAll() {
        fields.clear();
        update.clear();
        insert.clear();
        return this;
    }

    @Override
    public boolean ifTableExist(String table1) {
        try {
            database.rawQuery("SELECT * FROM " + table1, null);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public boolean isFieldExist(String fieldname, Object value) {
        if (database == null) return false;
        Cursor res = database.rawQuery("select " + fieldname + " from " + TABLENAME, null);
        if (res.getCount() == 0) {
            return false;
        } else {
            while (res.moveToNext()) {
                int data = check(value);
                if (data == 1) {
                    if ((Integer) value == res.getInt(0)) return true;
                } else if (data == 2) {
                    if (value.toString().equals(res.getString(0))) return true;

                }

            }
        }
        return false;
    }

    @Override
    public Cursor getField(String key) {
        if (database == null) return null;
        return database.rawQuery("select " + key + " from " + TABLENAME, null);
    }

    @Override
    public SqliteClosedHelper insertFields(String data, Object value) {
        insert.add(new Insert(data, value));
        return this;
    }


    public int check(Object data) {
        if (data instanceof Integer) {
            return 1;
        } else if (data instanceof String) {
            return 2;
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
            } else if (data.getValue() instanceof Long) {
                contentValues.put(data.getName(), (long) data.getValue());
            } else if (data.getValue() instanceof Float) {
                contentValues.put(data.getName(), (float) data.getValue());
            } else {
                throw new Error("Object not recognized must be int or string ");
            }
        }

        database.insertOrThrow(TABLENAME, null, contentValues);
        clearAll();
    }

    @Override
    public SqliteClosedHelper clearAllFields() {
        database.execSQL("delete from " + TABLENAME);
        return this;
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

    @Override
    public SqliteClosedHelper delete(String where, Object Value) {

        database.execSQL("delete from " + TABLENAME + " where " + where + "= '" + Value + "' ;");

        return this;
    }

//    Field[] fields1;
//
//    public <T> T createTable(T check) {
//        fields1 = check.getClass().getFields();
//
//
//        for (int i = 0; i < fields1.length - 2; i++) {
//
//            try {
//                Object o = fields1[i].get(check);
//                Log.e("ERR", o.toString());
//            } catch (IllegalAccessException e) {
//                Toast.makeText(context, "ex", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        return check;
//
//    }
//

    @Override
    public <T> T createTableFromClass(T table) {
        setTable(table.getClass().getSimpleName());
        clearAll();
        setTableFields(Constants.DEFAULT_ID, Wisdom.INTEGER(), Wisdom.PRIMARY_AUTOINCREMENT());
        Field[] items = table.getClass().getDeclaredFields();
        for (int i = 0; i < items.length - 2; i++) {
            String o = items[i].getName();
            setTableFields(o, getType(items[i].getType()));
        }
        create();
        return table;
    }

    @Override
    public TYPE getType(Class<?> type) {

        TYPE getType;
        if(type.isInstance(Integer.class)){
            Toast.makeText(context, "sadasd", Toast.LENGTH_SHORT).show();
            return Wisdom.INTEGER();
        }
        else
            return Wisdom.STRING();

//        switch (type.toString()) {
//            case "class java.lang.Double":
//                getType = Wisdom.CUSTOMTYPE("REAL");
//                break;
//            case "class java.lang.Float":
//                getType = Wisdom.CUSTOMTYPE("REAL");
//                break;
//            case "class java.lang.String":
//                getType = Wisdom.STRING();
//                break;
//            case "class java.lang.Integer":
//                getType = Wisdom.INTEGER();
//                break;
//            case "class java.lang.Long":
//                getType = Wisdom.INTEGER();
//                break;
//            default:
//                throw new Error("Object cannot be initialized on sqlite");
//
//        }
//        return getType;
    }
}
