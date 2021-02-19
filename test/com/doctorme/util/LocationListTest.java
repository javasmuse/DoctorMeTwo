package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationListTest {

    LocationList locationList = new LocationList();

    @Test
    public void allLocationsValidList() {
        assertNotNull(locationList.allLocations());
    }
}