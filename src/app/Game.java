package app;

import entities.Player;
import entities.Disease;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private int difficulty;
    private  ArrayList<Disease> diseaseList;
    private Scanner sc = new Scanner(System.in);

//    public Game(Player player, int difficulty, ArrayList<Disease> diseasesList) {
//        this.player = player;
//        this.difficulty = difficulty;
//
//    }


    public void start() {

    }


    public void play(int winningPointsRequired, int healthValue, ArrayList<Disease> diseaseList) {
        int score = healthValue;
        String userAnswer;

        while (score > 0) {
            // here we present scenerio and let the Dr fight the diseases
            for (int round = 0; round < diseaseList.size(); round++) {
                String location = diseaseList.get(round).getLocation();
                System.out.println("You find yourself in the:  " + location);
                System.out.println("Where you find:  " + diseaseList.get(round).description);
                System.out.println(diseaseList.get(round).question + "\n >>");
                userAnswer = sc.nextLine().strip();
                Commands.handleCommand(userAnswer, location);



                // for test purposes we will print the user Answer
                System.out.println(userAnswer + "  is what the player entered");
                // TODO some logic with the answer
                // TODO verify if win/lose
//stick answer into array to iterate through, basically to handle command and arguments
                //implement hint
                //
            }

        }
    }
    public void playAgain() {

    }

    private boolean didPlayerWin() {
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
