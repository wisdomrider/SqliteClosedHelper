package com.wisdomrider.sqliteclosedhelper;
// Created by WisdomRider (Avishek Adhikari) On 4/13/2018

public class Fields {
    String fieldname;
    PARAMETERS parameters;
    TYPE type;

    public Fields(String value, TYPE type, PARAMETERS parameters) {
        this.fieldname = value;
        this.type = type;
        this.parameters = parameters;

    }


    public Fields(String value, TYPE type) {
        this.fieldname = value;
        this.type = type;

    }

    public String getType() {
        return type.toString();
    }


    public String getFieldname() {
        return fieldname;
    }

    public String getParameters() {
        return parameters.toString();
    }


}
