package com.wisdomrider.sqliteclosedhelper;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Method<T> {
    private Field field;
    T table;

    public Method(Field f, T t) {
        field = f;
        this.table = t;
    }

    public boolean isString() {
        return getType().equals(String.class)||getType().equals(Boolean.class)||getType().equals(boolean.class);
    }

    public T getTable() {
        return table;
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

    public Class<Boolean> a() {
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

    public Object getSqliteValue() {
        field.setAccessible(true);
        try {
            return field.get(table);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public String getSqliteType() {
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return Constants.INTEGER;
        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class) || field.getType().equals(Float.class) || field.getType().equals(float.class)) {
            return Constants.REAL;
        } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
            return Constants.NUMERIC;
        } else {
            return Constants.TEXT;
        }
    }




    public String key() {
        return field.getName();
    }

    public boolean checkAnnotaions(Class class1) {
        return field.isAnnotationPresent(class1);
    }

    //CREATE TABLE if not exists `avi` (`private`	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE)
    public String getCreateTableQuery() {
        return " `" + key() + "` " + getSqliteType() + getAnnotations() + " ,";
    }

    public Object getType1() {
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return (Integer) getValue();
        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class) || field.getType().equals(Float.class) || field.getType().equals(float.class)) {
            return (Double) getValue();
        } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
            return (Long) getValue();
        } else {
            return (String) getValue();
        }

    }

    public boolean isPrimary() {
        if (field.isAnnotationPresent(SqliteAnnotations.Primary.class))
            return true;
        return false;
    }

    public boolean isNull() {
        if (getValue() == null)
            return true;
        if (!getType().equals(String.class) && String.valueOf(getValue()).equals("0"))
            return true;
        return false;
    }

    public String getAnnotations() {
        String annotations = "";
        if (field.isAnnotationPresent(SqliteAnnotations.Primary.class))
            annotations += " " + Constants.PRIMARY + " ";
        if (field.isAnnotationPresent(SqliteAnnotations.Nullable.class) && !field.getAnnotation(SqliteAnnotations.Nullable.class).isNullable())
            annotations += " " + Constants.NOT_NULLABLE + " ";
        if (field.isAnnotationPresent(SqliteAnnotations.AutoIncrement.class))
            annotations += " " + Constants.AUTO_INCREMENT + " ";
        if (field.isAnnotationPresent(SqliteAnnotations.Unique.class))
            annotations += " " + Constants.UNIQUE + " ";
        return annotations;
    }

    public String getInsertTableQuery() {

        return "";
    }


}
