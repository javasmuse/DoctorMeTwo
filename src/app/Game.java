package app;

import entities.Player;

public class Game {

    private Player player;
    private int difficulty;

    public Game(Player player, int difficulty) {
        this.player = player;
        this.difficulty = difficulty;
    }

    public void start(){

    }

    public void play(){

    }

    public void playAgain(){

    }

    private boolean didPlayerWin(){
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
