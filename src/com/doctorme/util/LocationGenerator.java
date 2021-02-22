package com.doctorme.util;

import com.doctorme.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationGenerator {
    private LocationList ll = new LocationList();
    private List<Location> listLocas = new ArrayList<>();
    private Location currLoc;

    // STOCK LOCATION LIST - expansion possible for user or designer selected 'topics or level' - alternate xmls
    public void bringLocations() {
        listLocas = ll.allLocations();
    }

    // start location from a random choice from the list
    public Location startLocation() {
        return randoLocation(listLocas);
    }

    // move to next room
    public Location nextLocation(String locationName) {

        for (int i = 0; i < listLocas.size(); i++) {
            if (locationName.equals("?????")) {
                locationName = "Secret Room";
            }
            if (locationName.equals(listLocas.get(i).getName())) {
                currLoc = listLocas.get(i);
                return currLoc;
            }
        }

        return currLoc;
    }

    // retrieve random Question from current room list
    public Location randoLocation(List<Location> locals) {
        return locals.get(randomNumber(locals.size()));
    }

    // RANDOM number generator
    public int randomNumber(int local) {
        return (int) (Math.random() * local);
    }

    // ACCESSORS
    public LocationList getLl() {
        return ll;
    }

    public List<Location> getListLocas() {
        return listLocas;
    }

    public Location getCurrLoc() {
        return currLoc;
    }
}
