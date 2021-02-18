package com.doctorme.entities;

import java.util.List;

public class LeaderBoard {

    Player player = new Player();
    // FIELDS
    String name = player.getName();
    String points;
    String badges;
    String time;
    String level;

    // CONSTRUCTORS

    public LeaderBoard(String name, String points, String badges) {
        this.name = name;
        this.points = points;
        this.badges = badges;
    }


    // ACCESSORS

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

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
