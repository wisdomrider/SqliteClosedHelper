package com.wisdomrider.sqliteclosedhelper.newVersion;

import android.util.Base64;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Method<T> {
    private Field field;
    T table;

    public Method(Field f, T t) {
        field = f;
        this.table = t;
    }


    public Type getType() {
        return field.getType();
    }

    public Method setValue(Object o) {
        field.setAccessible(true);
        try {
            field.set(table, o);
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage());
        }
        return this;
    }

    public Object getValue() {
        field.setAccessible(true);
        try {
            return field.get(table);
        } catch (IllegalAccessException e) {
            return null;
        }
    }


    public String key() {
        return field.getName();
    }

    public String getUniqueKey() {
        String s1 = table.getClass().getName();
        String s3 = "";
        try {
            s3 = table.getClass().getDeclaredFields()[0].get(table).toString();
        } catch (Exception e) {

        }
        String s2 = table.getClass().getDeclaredFields()[0].getType().toString();
        int l1 = s1.length() + s2.length();
        s1 = s1 + s3 + l1 + s2;
        return Base64.encodeToString(s1.getBytes(), Base64.URL_SAFE);
    }


}
