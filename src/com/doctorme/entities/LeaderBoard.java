package com.doctorme.entities;

import java.util.List;

public class LeaderBoard {

    private Player player = new Player();
    private String name;
    private String points;
    private String badges;
    private String time;
    private String level;

    // CONSTRUCTORS
    public LeaderBoard(String name, String points, String badges) {
        this.name = name;
        this.points = points;
        this.badges = badges;
    }

    // ACCESSORS
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    // toString

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "player=" + player +
                ", points='" + points + '\'' +
                ", badges='" + badges + '\'' +
                ", time='" + time + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
