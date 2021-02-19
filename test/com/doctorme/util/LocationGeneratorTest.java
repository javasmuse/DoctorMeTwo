package com.doctorme.util;

import com.doctorme.app.Game;
import com.doctorme.entities.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LocationGeneratorTest {
    LocationGenerator locationGenerator = new LocationGenerator();

    @Test
    public void bringLocations() {
        assertNotNull(locationGenerator.getListLocas());
    }

    @Test
    public void randomNumber() {
        assertNotNull(locationGenerator.randomNumber(5));
    }
}