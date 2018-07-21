package com.example.vitor.dummycook;

import java.io.Serializable;

public class Pessoa implements Serializable{
    private final String mName;
    private final String mCity;
    private final String mDescription;
    private int mAge;

    public Pessoa(String name, String city, String description, int age) {
        mName = name;
        mCity = city;
        mDescription = description;
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public String getCity() {
        return mCity;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getAge() {
        return mAge;
    }

    public void incrementAge() {
        mAge++;
    }
}
