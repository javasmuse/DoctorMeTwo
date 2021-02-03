package app;

import entities.Pathogen;
import entities.Player;
import util.Colors;
import util.GameConstants;
import util.Output;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Game {
    private int id = UUID.randomUUID().hashCode();
    private Player player;
    private int difficulty;
    private Scanner sc = new Scanner(System.in);

    public Game(){
        super();
    }

    public Game(Player player, int difficulty) {
        this();
        setPlayer(player);
        setDifficulty(difficulty);
    }


    public void play(int winningPointsRequired, int healthValue, ArrayList<Pathogen> pathogenList) {
        int score = healthValue;
        String userAnswer;

        while (!isGameEnd(this.getPlayer(), winningPointsRequired)) {
            // here we present scenerio and let the Dr fight the pathogens
            for (int round = 0; round < pathogenList.size(); round++) {
                Pathogen currentThreat = pathogenList.get(round);
                String location = currentThreat.getLocation();
                Output.printColor("You find yourself in the:  " + location, Colors.ANSI_BLUE, true);
                Output.printColor("Where you find:  " + currentThreat.getDescription() + "\n",  Colors.ANSI_BLUE, true);

                Output.printColor(currentThreat.getQuestion() + "\n Type your answer >> ", Colors.ANSI_YELLOW, false);
                // Save the player's answer
                userAnswer = sc.nextLine().strip();

                // If correct, inflict damage
                if(isCorrect(currentThreat, userAnswer)){
                    this.getPlayer().attack(currentThreat);
                    Output.printColor("You inflict enough "
                                    + player.getStrength()
                                    + " damage to the pathogen"
                                    + " so that it dies a slow death.",
                            Colors.ANSI_GREEN, true);

                    // Increase player points
                    this.getPlayer().addPoints(currentThreat.getPoints());
                } else {
                    // Otherwise pathogen inflicts damage
                    currentThreat.attack(this.getPlayer());
                    Output.printColor("The deadly pathogen inflicts "
                            + " damage to you."
                            + " We cannot fail!"
                            + " Your remaining health is "
                            + this.getPlayer().getHealth(),
                            Colors.ANSI_RED,
                            true);
                }



//                boolean isValidInput = Commands.handleCommand(userAnswer, location);
//                if(!isValidInput){
//                    continue;
//                }

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

    private boolean isGameEnd(Player player, int requiredPoints) {
        // TODO Finalize winning conditions
        if(isWin(player, requiredPoints)){
            // Player has won
            Output.printColor(player.getName() + " won the game!", Colors.ANSI_CYAN, true);
            // Show game results

            return true;

        } else if (isLose(player)){
            // Actions to do on lose
            Output.printColor(player.getName() + " lost the game :(((", Colors.ANSI_RED, true);

            // Show game results


            return true;
        }

        return false;
    }

    private boolean isWin(Player player, int requiredPoints) {
        if(player.getPoints() >= requiredPoints){
            return true;
        } else {
            return false;
        }
    }

    private boolean isLose(Player player) {
        if(player.getHealth() < 1){
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrect(Pathogen pathogenWithQuestion, String answer){
        String correctAnswer = pathogenWithQuestion.getCorrectAnswer().toLowerCase().trim();
        answer = answer.toLowerCase().trim();
        if(correctAnswer.contains(answer)){
            System.out.println(answer.toUpperCase() + " is correct");
            return true;
        } else {
            System.out.println(answer.toUpperCase() + " is wrong. " + correctAnswer.toUpperCase()
            + " is the correct answer.");
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
