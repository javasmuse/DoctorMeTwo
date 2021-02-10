package com.doctorme.entities;

import java.util.List;

public class Player {

    private String name;
    private int points;
    private List<Badge> badges;

    public Player(){
        //default
    }

    public Player(String name){
        //TODO: fill me in
    }

    public Player(String name, int points) {
        //TODO: fill me in
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", badges=" + badges +
                '}';
    }
}