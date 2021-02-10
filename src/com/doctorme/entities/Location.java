package com.doctorme.entities;

import java.util.List;

public class Location {
    private String description;
    private List<Question> questions;
    private List<Location> exit;
    private int tierLevel;

    public Location(){
        //default constructor
    }

    public Location(String description, List<Question> questions, List<Location> exit, int tierLevel) {
        this.description = description;
        this.questions = questions;
        this.exit = exit;
        this.tierLevel = tierLevel;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Location> getExit() {
        return exit;
    }

    public void setExit(List<Location> exit) {
        this.exit = exit;
    }

    public int getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(int tierLevel) {
        this.tierLevel = tierLevel;
    }
}

