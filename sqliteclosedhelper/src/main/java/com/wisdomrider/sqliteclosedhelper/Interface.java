package com.wisdomrider.sqliteclosedhelper;

/*
   Created By WisdomRider(Avishek Adhikari)

    Email : avishekzone@gmail.com

    Make Sure to Star Us On Github :
       https://github.com/wisdomrider/SqliteClosedHelper

     Credit Me SomeWhere In Your Project :)

     Thanks !!
*/

import java.util.ArrayList;

public interface Interface {
    void Query(String q);

    <T> ArrayList<Method> decompile(T t);


    <T> SqliteClosedHelper createTable(T t) throws Exception;

    <T> SqliteClosedHelper insertTable(T t);

    <T> SqliteClosedHelper updateTable(T t);

    <T> SqliteClosedHelper updateTable(T data,T condition,String CASE);

    <T> ArrayList<T> whereAND(T t);

    <T> SqliteClosedHelper delete(T t,String CASE);

    <T> SqliteClosedHelper delete(T t);

    <T> ArrayList<T> whereOR(T t);

    <T> ArrayList<T> getAll(T t);

    <T> SqliteClosedHelper removeAll(T t);

    <T> SqliteClosedHelper updateAll(ArrayList<T> t);

    <T> SqliteClosedHelper insertAll(ArrayList<T> t);

    <T> SqliteClosedHelper renameTable(T t, String newName);


    void closeDatabase();


}
