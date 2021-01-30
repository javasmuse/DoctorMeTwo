package app;

import entities.Disease;
import entities.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class DoctorMeApp {

    private final String GAME_INTRODUCTION =
            "     Doctor Me needs your help!\n" +
                    "     You are on a mission to save the body\n" +
                    "     Here are some tips on how to play...\n" +
                    "     ~~~~~~  The Commands ARE:  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "     HELP  -  displays more information on commands\n" +
                    "     HINT  -  gives you a hint on what to do next\n" +
                    "     GET <item>  -  gets the item from your inventory to see if its useful\n" +
                    "     INVENTORY  -  displays the items you have\n"+
                    "     _________________________________________________________________________";

    private final String GAME_INTRODUCTION_TWO =
            "\nThe walls shake as the roar of borborygmus envelops the room.\n" +
                    "As you look around, you notice the walls have many folds--rugae, perhaps?\n" +
                    "The floor bubbling with acid, you realize you're in the stomach.\n" +
                    "What discoveries await as you explore the inner-workings of the human body?\n";

    private final int WINNING_POINTS_REQUIRED = 100;
    private int HEALTHVALUE = 100; // initiates health value
    private int DIFFICULTY = 50;
    public static final String BOLD_RED = "\033[1;91m";
    public static final String RESET = "\033[0m";

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DoctorMeApp app = new DoctorMeApp();
        app.playDrMe();

    }

    public void playDrMe() {
        System.out.println("Hello model.Player, What is you name?"+"\n >>");
        String playerName = sc.nextLine().strip();
        // initialize player
        Player player = new Player(playerName, HEALTHVALUE);

        // Create the game object, passing in one player with "normal"
        // difficulty represented as 50
        Game game = new Game();

        // Display game introduction related information
        System.out.println("Hello welcome to Dr Me " + BOLD_RED + playerName + RESET);
        System.out.println(GAME_INTRODUCTION);
        System.out.println(GAME_INTRODUCTION_TWO);

        // Read in the XML file
        XMLController xmlc = new XMLController();
        game.play(HEALTHVALUE, xmlc.readXML());


        // game loop - possibly delegate to game object
        //while(!isWin(WINNING_POINTS_REQUIRED)){
        // do a game turn
        //user input
        //fight cell

        //After each fight, check player health
        // to see if player died
        // If so, game over
        // if player died => end game

        //end turn actions
        //}

    }

    private boolean isWin(Player player, int requiredPoints) {
//        if(player.getPoints() >= requiredPoints){
//            return true;
//        } else {
//            return false;
//        }
        // return false so Java compiles for now
        return false;
    }

//    public void into(){
//
//    }
//
//    public void getUserInput(){
//
//    }
//
//    public void getXML(){
//
//    }

}