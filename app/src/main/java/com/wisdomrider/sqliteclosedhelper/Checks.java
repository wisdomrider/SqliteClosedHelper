package com.wisdomrider.sqliteclosedhelper;


public class Checks {
    @SqliteAnnotations.Primary
    @SqliteAnnotations.AutoIncrement
    int id;

    String name;

    float age;

    String place;

    boolean hey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isHey() {
        return hey;
    }

    public void setHey(boolean hey) {
        this.hey = hey;
    }
}
