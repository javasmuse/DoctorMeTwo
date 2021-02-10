package com.doctorme.entities;
/**
 * Badge.java implements Badges that the player can collect
 * as they progress through the game. Unlocking all the badges
 * allows them to access the final level and attempt to win the
 * game. Badges have two attributes: a name (String) and an image
 * file (String).
 * <p>
 * Author: Bradley Pratt
 * Last Edited: 02/10/2021
 */

public class Badge {
    private String name;
    //in string format so GUI can load the file
    private String imageFile;

    public Badge(){
        setName("NULL");
    }

    public Badge(String name) {
        setName(name);
    }

    public Badge(String name, String imageFile) {
        setName(name);
        setImageFile(imageFile);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getImageFile() {
        return imageFile;
    }

    private void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
