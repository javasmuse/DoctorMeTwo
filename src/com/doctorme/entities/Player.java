package com.doctorme.entities;
/**
 * Player.java implements the user's current
 * profile state in the game. Its attributes include
 * a name (of the user), current points total, and a
 * list of badges the user has earned thus far.
 * <p>
 * Author: Bradley Pratt
 * Last Edited: 02/10/2021
 */
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int points = 0;
    private List<Badge> badges;

    //*************** CONSTRUCTORS/ DESTRUCTORS ***************
    public Player(){
        setPoints(0);
        badges = new ArrayList<>();
    }

    public Player(String name){
        setName(name);
        setPoints(0);
        badges = new ArrayList<>();
    }

    public Player(String name, int points) {
        setName(name);
        setPoints(points);
        badges = new ArrayList<>();
    }

    //*************** HELPER METHODS ***************
    public void addBadge(Badge newBadge){
        if (!hasBadge(newBadge)){
            badges.add(newBadge);
        }else{
            System.out.println("Player already has the " + newBadge + " badge!");
        }
    }

    private boolean hasBadge(Badge newBadge){
        return getBadges().contains(newBadge);
    }

    //*************** ACCESSOR METHODS ***************
    public String getName() {
        return name;
    }

    private void setName(String name) {
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

    private void setBadges(List<Badge> badges) {
        getBadges().clear();
        for (Badge bad: badges){
            addBadge(bad);
        }
    }

    @Override
    public String toString() {
        String output = getName() + "\n\tPoints: " + getPoints() + "\n\tBadges: ";
        if (badges.isEmpty()){
            output += "NONE";
        }else {
            for (Badge bad: badges)
                output += bad + ", ";
        }

        return output;
    }
}