package com.doctorme.client;

import com.doctorme.GUI.GameGUI;
import com.doctorme.app.Game;
import com.doctorme.entities.Player;

import javax.xml.transform.TransformerException;
import java.util.*;


public class DoctorMeApp {

    public static void main(String[] args) throws InterruptedException, TransformerException {
        Game game = new Game();
        game.startGame();
    }
}