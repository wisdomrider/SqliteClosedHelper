package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/14/2018

public class Insert {
    String name;
    Object value;

    public Insert(String name,Object value){
    this.name=name;
    this.value=value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
