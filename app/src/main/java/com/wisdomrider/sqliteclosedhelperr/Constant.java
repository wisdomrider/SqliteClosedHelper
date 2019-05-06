package com.wisdomrider.sqliteclosedhelperr;

import com.wisdomrider.sqliteclosedhelper.SqliteAnnotations;

public class Constant {

    @SqliteAnnotations.Primary
    String name;

    String value;


    public String getName() {
        return name;
    }

    public Constant setName(String name) {
        this.name = name;

        return this;
    }

    public String getValue() {
        return value;
    }

    public Constant setValue(String value) {
        this.value = value;
        return this;
    }

}
