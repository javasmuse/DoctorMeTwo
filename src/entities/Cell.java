package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {
    private String name;
    private String description;
    private boolean delay;
    private boolean selfDamage;
    private List defeats;
    private String location;
    private static ArrayList<Cell> cellList;

    public Cell(){

    }

    public Cell(String name, String description,boolean delay, boolean selfDamage, List defeats, String location) {
        this.name = name;
        this.description = description;
        this.delay = delay;
        this.selfDamage = selfDamage;
        this.defeats = defeats;
        this.location = location;
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

    public boolean isSelfDamage() {
        return selfDamage;
    }

    public void setSelfDamage(boolean selfDamage) {
        this.selfDamage = selfDamage;
    }

    public List getDefeats() {
        return defeats;
    }

    public void setDefeats(List defeats) {
        this.defeats = defeats;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCellList(ArrayList<Cell> cellList) {
        this.cellList = cellList;
    }
    @Override
    public String toString() {
        return "Cell{ " +
                "name=  " + name + '\'' +
                ", description= " + description + '\'' +
                ", selfDamage=  " + selfDamage +
                ", defeats=  " + defeats +
                ", location=  " + location + '}';
    }

}
