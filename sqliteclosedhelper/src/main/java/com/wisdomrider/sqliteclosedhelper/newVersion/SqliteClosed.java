package com.wisdomrider.sqliteclosedhelper.newVersion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wisdomrider.sqliteclosedhelper.Fields;
import com.wisdomrider.sqliteclosedhelper.helpmesqlite;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SqliteClosed implements Interface {
    Context c;
    String DB_NAME;
    SQLiteDatabase database;
    helpmesqlite Sqlite;


    public SqliteClosed(Context c, String DB_NAME) {
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
        Collections.reverse(fields);
        for (Field f : fields) {
            methods.add(new Method(f, t));
        }
        return methods;
    }

    @Override
    public <T> SqliteClosed createTable(T t) {
        ArrayList<Method> methods = decompile(t);
        String var_name = "";
        for (Method m : methods) {

        }

        return this;
    }
}
