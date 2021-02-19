package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class BadgeGeneratorTest {

    BadgeGenerator badgeGenerator = new BadgeGenerator();

    @Test
    public void allBadgesValidListReadIn() {
        assertNotNull(badgeGenerator.allBadges());
    }
}