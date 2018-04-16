package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018

public  class Wisdom  {

    public static TYPE TEXT(){
        return new TYPE("TEXT");
    }
    public static TYPE INTEGER(){
        return new TYPE("INTEGER");
    }
    public static TYPE DATETIME(){
        return new TYPE("DATETIME");
    }
    public static TYPE STRING(){
        return new TYPE("TEXT");
    }
    public  static TYPE CUSTOMTYPE(String CUSTOMPARAMETER){ return  new TYPE(CUSTOMPARAMETER+" "); }
    public  static PARAMETERS NOTNULL(){
        return  new PARAMETERS("not null ");
    }
    public  static PARAMETERS CUSTOM(String CUSTOMPARAMETER){
        return  new PARAMETERS(CUSTOMPARAMETER+" ");
    }


    public  static PARAMETERS PRIMARY_AUTOINCREMENT(){
        return  new PARAMETERS("PRIMARY KEY AUTOINCREMENT ");
    }
    public  static PARAMETERS PRIMARY(){
        return  new PARAMETERS("PRIMARY ");
    }
    public  static PARAMETERS NONE(){
        return  new PARAMETERS("");
    }


}


class PARAMETERS{
    String data;
    public PARAMETERS(String a){data=a;}
    public  String toString(){return  data;}
}


class TYPE{
    String data;
    public TYPE(String a){data=a;}
    public  String toString(){return  data;}
}
