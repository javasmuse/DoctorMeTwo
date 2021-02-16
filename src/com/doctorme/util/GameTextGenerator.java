package com.doctorme.util;

public class GameTextGenerator {
    private GameText text = new GameText();

    // GAME TEXT DISPLAY  - Welcome - Intro - Instructions
    public String printInstructions(){ return (text.readInstructions().get(2)); }

    public String printWelcome(){ return (text.readInstructions().get(0)); }

    public String printIntro(){ return text.readInstructions().get(1); }
}
