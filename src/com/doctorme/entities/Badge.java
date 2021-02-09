package com.doctorme.entities;

public class Badge {
    private String name;
    public Badge(){
        //default constructor
    }

    public Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
