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


    public void play(int healthValue, ArrayList<Disease> diseaseList) {
        int score = healthValue;
         String userAnswer;

        while (score > 0){
            // here we present scenerio and let the Dr fight the diseases
            for(int round = 0; round < diseaseList.size(); round++ ){

                System.out.println("You find yourself in the:  "+ diseaseList.get(round).location);
                System.out.println("Where you find:  "+ diseaseList.get(round).description);
                System.out.println(diseaseList.get(round).question+"\n >>");
                userAnswer = sc.nextLine().strip();
                // for test purposes we will print the user Answer
                System.out.println(userAnswer+"  is what the player entered");
                // TODO some logic with the answer
                // TODO verify if win/lose

            }

        }
    public void play() {

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
