package com.wisdomrider.sqliteclosedhelper;

import java.util.ArrayList;

public interface Interface {
    void Query(String q);

    <T> ArrayList<Method> decompile(T t);

    <T> SqliteClosedHelper createTable(T t);

    <T> SqliteClosedHelper insertTable(T t);

    <T> SqliteClosedHelper updateTable(T t);

    <T> ArrayList<T> whereAND(T t);

    <T> ArrayList<T> whereOR(T t);

    <T> ArrayList<T> getAll(T t);

    <T> SqliteClosedHelper renameTable(T t, String newName);

    void closeDatabase();


}
