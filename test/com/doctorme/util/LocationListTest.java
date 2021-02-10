package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationListTest {

    LocationList llTest = new LocationList();

    @Test
    public void allLocations() {
        assertNotNull(llTest.allLocations("resources/locations.xml", "locations"));
    }
}