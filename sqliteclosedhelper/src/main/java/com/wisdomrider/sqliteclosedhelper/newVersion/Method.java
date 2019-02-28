package com.wisdomrider.sqliteclosedhelper.newVersion;

import android.util.Base64;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Method<T> {
    private Field field;
    T table;
    String uniqueID;

    public Method(Field f, T t) {
        field = f;
        this.table = t;
        try {
            uniqueID=t.getClass().getDeclaredFields()[0].get(t).toString();
        } catch (IllegalAccessException e) {
            uniqueID="";
        }
    }


    public Type getType() {
        return field.getType();
    }

    public Method setValue(Object o) {
        field.setAccessible(true);
        try {
            field.set(table, o);
            uniqueID=o.toString();
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage());
        }
        return this;
    }
    public Class<Boolean> a(){
        return Boolean.class;
    }


    public Object getValue() {
        field.setAccessible(true);
        try {
            return field.get(table);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public String getSqliteType() {
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return " INTEGER ";
        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class) || field.getType().equals(Float.class) || field.getType().equals(float.class))
        {
            return " REAL ";
        }
        else if(field.getType().equals(Long.class)||field.getType().equals(long.class)){
            return  " NUMERIC ";
        }
        else{
            return  " TEXT ";
        }
    }


    public String key() {
        return field.getName();
    }

    public String getUniqueKey() {
        String s1 = table.getClass().getName();
        String s2 = table.getClass().getDeclaredFields()[0].getType().toString();
        int l1 = s1.length() + s2.length();
        s1 = s1 + uniqueID + l1 + s2;
        return Base64.encodeToString(s1.getBytes(), Base64.URL_SAFE);
    }


}
