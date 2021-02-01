package app;

import entities.Player;
import entities.Disease;
import util.Colors;
import util.GameConstants;
import util.Output;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Game {

    private Player player;
    private int difficulty;
    int id = UUID.randomUUID().hashCode();
    private Scanner sc = new Scanner(System.in);

    public Game(Player player, int difficulty) {
        this.player = player;
        this.difficulty = difficulty;
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

                boolean isValidInput = Commands.handleCommand(userAnswer, location);
                if(!isValidInput){
                    continue;
                }



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

    public void playIntroduction(String playerName) throws InterruptedException {
        // Display game introduction related information
        Output.printColor("Hello welcome to Dr Me ", Colors.ANSI_RED, false);
        Output.printColor(playerName, Colors.ANSI_RED, true);

        // Prints a loading display sequence
        Output.printLoading(3);

        // Start printing the games story
        Output.printColor(GameConstants.GAME_INTRODUCTION, Colors.ANSI_BLUE, true);

        Output.printLoading(5);

        Output.printColor(GameConstants.GAME_INTRODUCTION_TWO, Colors.ANSI_BLUE, true);

        Output.printLoading(5);
    }

    private boolean isWin(Player player, int requiredPoints) {
        if(player.getPointsByGameId(this.id) >= requiredPoints){
            return true;
        } else {
            return false;
        }
    }


    public Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }
}
