package com.doctorme.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game();

    @Test
    public void questionsByType() {
        assertNotNull(game.questionsByType("astronomy"));
    }
}