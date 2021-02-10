package com.doctorme.entities;

import java.util.List;

public class Location {
    // FIELDS
    private int id;
    private String name;
    private String description;
    private int tierLevel;
    private List<String> roomLeadTo;
    private List<String> exits;

    // CONSTRUCTOR
    public Location(int id, String name, String description, int tierLevel, List<String> roomLeadTo, List<String> exits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tierLevel = tierLevel;
        this.roomLeadTo = roomLeadTo;
        this.exits = exits;
    }

    // ACCESSORS

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(int tierLevel) {
        this.tierLevel = tierLevel;
    }

    public List<String> getRoomLeadTo() {
        return roomLeadTo;
    }

    public void setRoomLeadTo(List<String> roomLeadTo) {
        this.roomLeadTo = roomLeadTo;
    }

    public List<String> getExits() {
        return exits;
    }

    public void setExits(List<String> exits) {
        this.exits = exits;
    }

    // toString  - the Ugly One - fix later
    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tierLevel=" + tierLevel +
                ", roomLeadTo=" + roomLeadTo +
                ", exits=" + exits +
                '}';
    }
}
