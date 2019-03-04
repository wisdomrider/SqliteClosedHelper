package com.wisdomrider.sqliteclosedhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class SqliteClosedHelper implements Interface {
    Context c;
    String DB_NAME;
    SQLiteDatabase database;
    helpmesqlite Sqlite;


    public SqliteClosedHelper(Context c, String DB_NAME) {
        this.c = c;
        this.DB_NAME = DB_NAME;
        Sqlite = new helpmesqlite(c, DB_NAME);
        database = Sqlite.getDatabase();
    }

    @Override
    public void Query(String q) {
        database.execSQL(q);


    }

    @Override
    public <T> ArrayList<Method> decompile(T t) {
        ArrayList<Method> methods = new ArrayList<>();
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(t.getClass().getDeclaredFields()).subList(0, t.getClass().getDeclaredFields().length - 2));
        for (Field f : fields) {
            Method method = new Method(f, t);
            if (!method.checkAnnotaions(MethodAnnotations.Exclude.class))
                methods.add(method);
        }
        return methods;
    }

    @Override
    public <T> SqliteClosedHelper createTable(T t) {
        ArrayList<Method> methods = decompile(t);
        StringBuilder var_name = new StringBuilder("CREATE TABLE if not exists `" + t.getClass().getSimpleName() + "` (");
        for (Method m : methods) var_name.append(m.getCreateTableQuery());
        var_name = new StringBuilder(var_name.substring(0, var_name.length() - 1) + " )");
        Log.e("QUERY", var_name.toString());
        Query(var_name.toString());
        return this;
    }

    @Override
    public <T> SqliteClosedHelper insertTable(T t) {
        ArrayList<Method> methods = decompile(t);
        StringBuilder var_name = new StringBuilder("INSERT INTO `" + t.getClass().getSimpleName() + "`(");
        for (Method m : methods) {
            if (m.getValue() != null)
                var_name.append("`" + m.key() + "`,");
        }
        var_name = new StringBuilder(var_name.substring(0, var_name.length() - 1) + ") VALUES (");
        for (Method m : methods) {
            if (!m.isNull())
                if (m.isString())
                    var_name.append("'" + m.getValue() + "' ,");
                else
                    var_name.append(m.getValue() + ",");
        }
        var_name = new StringBuilder(var_name.substring(0, var_name.length() - 1) + ")");
        Log.e("QUERY", String.valueOf(var_name));
        database.execSQL(String.valueOf(var_name));
        return this;
    }


    @Override
    public <T> SqliteClosedHelper updateTable(T t) {
        ArrayList<Method> methods = decompile(t);
        StringBuilder primary = new StringBuilder(" WHERE ");
        StringBuilder var_name = new StringBuilder("UPDATE " + t.getClass().getSimpleName() + " SET ");
        for (Method m : methods) {
            String key = "";
            if (!m.isNull()) {
                if (m.isString())
                    key = m.key() + "='" + m.getValue() + "',";
                else
                    key += m.key() + "=" + m.getValue() + ",";
                if (!m.isPrimary()) var_name.append(key);
                else {
                    primary.append(key);
                }
            }
        }
        Cursor cursor = database.rawQuery("SELECT * FROM " + t.getClass().getSimpleName() + primary.toString().replace(",", ""), null);
        if (cursor.getCount() == 0) {
            insertTable(t);
            return this;
        }
        var_name = new StringBuilder(var_name.substring(0, var_name.length() - 1));
        primary = new StringBuilder(primary.substring(0, primary.length() - 1));
        var_name.append(primary);
        Log.e("ERR", String.valueOf(var_name));
        database.execSQL(String.valueOf(var_name));
        return this;
    }


    //    SELECT * FROM 'Checks' where name = '2';
    @Override
    public <T> ArrayList<T> whereAND(T t) {
        return where(t, Constants.AND, 4);
    }

    @Override
    public <T> ArrayList<T> whereOR(T t) {
        return where(t, Constants.OR, 3);
    }

    @Override
    public <T> ArrayList<T> getAll(T t) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + t.getClass().getSimpleName(), null);
        return getArrayFromCursor(cursor, t, decompile(t));
    }

    @Override
    public void closeDatabase() {
        database.close();
    }

    @Override
    public <T> SqliteClosedHelper renameTable(T t, String newName) {
        /* Make sure to change class name too otherwise it will crash */
        Query("ALTER TABLE " + t.getClass().getSimpleName() + " RENAME TO " + newName + ";");
        return this;
    }


    public <T> ArrayList<T> where(T t, String what, int position) {
        StringBuilder var_name = new StringBuilder("SELECT * FROM " + t.getClass().getSimpleName() + " WHERE ");
        ArrayList<Method> methods = decompile(t);
        for (Method m : methods) {
            if (!m.isNull()) {
                if (m.isString())
                    var_name.append(m.key()).append(" = '").append(m.getValue()).append("' " + what + " ");
                else
                    var_name.append(m.key()).append(" = ").append(m.getValue()).append(" " + what + " ");
            }

        }
        var_name = new StringBuilder(var_name.substring(0, var_name.length() - position));
        Cursor c = database.rawQuery(String.valueOf(var_name), null);
        return getArrayFromCursor(c, t, methods);
    }


    private <T> ArrayList<T> getArrayFromCursor(Cursor c, T t, ArrayList<Method> methods) {
        ArrayList<T> list = new ArrayList<>();
        if (c.getCount() == 0) return list;
        while (c.moveToNext()) {
            int i = 0;
            for (Method m : methods) {
                if (m.getType().equals(String.class)) m.setValue(c.getString(i));
                else if (m.getType().equals(Integer.class) || m.getType().equals(int.class))
                    m.setValue(c.getInt(i));
                else if (m.getType().equals(Double.class) || m.getType().equals(double.class))
                    m.setValue(c.getDouble(i));
                else if (m.getType().equals(Float.class) || m.getType().equals(float.class))
                    m.setValue(c.getFloat(i));
                else if (m.getType().equals(Long.class) || m.getType().equals(long.class))
                    m.setValue(c.getLong(i));
                else if (m.getType().equals(boolean.class) || m.getType().equals(Boolean.class))
                    m.setValue(Boolean.parseBoolean(c.getString(i)));
                i++;
            }
            list.add((T) methods.get(0).table);
        }
        return list;

    }
}
