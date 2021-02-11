package com.doctorme.util;

import junit.framework.TestCase;

public class GameTextTest extends TestCase {
    GameText text = new GameText();

    public void testReadInstructions() {
     assertNotNull(text.readInstructions());
    }
}