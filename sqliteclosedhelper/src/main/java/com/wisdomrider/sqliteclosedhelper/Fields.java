package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018

public class Fields {
    String tablename,value,defaultvalue;
    int intvalue=0, defaultintvalue=0,parameters=0;

    public Fields(String tablename,String value,String defaultvalue,int parameters){
        this.tablename=tablename;
        this.value=value;
        this.defaultvalue=defaultvalue;
        this.parameters=parameters;

    }
    public Fields(String tablename,int value,int defaultvalue,int parameters){
        this.tablename=tablename;
        this.intvalue=value;
        this.defaultintvalue=defaultvalue;
        this.parameters=parameters;

    }
    public  boolean isInt(){
        if(intvalue==0&&value!=null) return false;
        return true;
    }
    public boolean isString(){
        if(isInt()){
            return false;
        }
        return  true;
    }

    public int getDefaultintvalue() {
        return defaultintvalue;
    }

    public int getIntvalue() {
        return intvalue;
    }

    public int getParameters() {
        return parameters;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public String getTablename() {
        return tablename;
    }

    public String getValue() {
        return value;
    }

}
