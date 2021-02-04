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
    private static Player player;
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
        // Initiate primary game loop, check game ending conditions each time
        while (!isGameEnd(this.getPlayer(), winningPointsRequired)) {
            // here we present scenerio and let the Dr fight the pathogens
            for (int round = 0; round < pathogenList.size(); round++) {
                // Handle the current threat's scenario and question
                Pathogen currentThreat = pathogenList.get(round);
                askPathogenQuestion(currentThreat);

                // Receive the player's input
                getUserInput();

            }
        }
    }

    private void askPathogenQuestion(Pathogen currentThreat) {
        String location = currentThreat.getLocation();
        Output.printColor("You find yourself in the:  " + location, Colors.ANSI_BLUE, true);
        Output.printColor("Where you find:  " + currentThreat.getDescription() + "\n",  Colors.ANSI_BLUE, true);
        Output.printColor(currentThreat.getQuestion() + "\n Type your answer >> ", Colors.ANSI_YELLOW, false);
    }

    // Gets the user raw input and sends to handler
    private void getUserInput() {
        boolean isValidInput = false;
        // Keep asking for input until it is a valid command (exluding, help, hint)
        // Once valid, loop will break and thread will continue
        while(!isValidInput){
            // Set the player's answer
            String userAnswer = sc.nextLine().strip();
            String pathogenName = this.getPlayer().getName();
            // True if primary command entered, false if bad command or hint/help entered
            isValidInput = Commands.handleCommand(userAnswer, pathogenName);
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

    public static void setPlayer(Player playerParam) {
        player = playerParam;
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
