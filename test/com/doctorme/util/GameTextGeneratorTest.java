package com.doctorme.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTextGeneratorTest {

    GameTextGenerator gameTextGenerator = new GameTextGenerator();

    @Test
    public void printInstructionsReadInText() {
        assertNotNull(gameTextGenerator.printInstructions());
    }

    @Test
    public void printWelcomeReadInText() {
        assertNotNull(gameTextGenerator.printWelcome());
    }

    @Test
    public void printIntroReadInText() {
        assertNotNull(gameTextGenerator.printIntro());
    }
}