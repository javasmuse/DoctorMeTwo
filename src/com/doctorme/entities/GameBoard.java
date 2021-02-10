package com.doctorme.entities;

import java.util.List;

public class GameBoard {
    private List<Location> locationsGame;
    private List<Question> questionsGame;

    // CONSTRUCTORS

    public GameBoard(List<Location> locationsGame, List<Question> questionsGame) {
        this.locationsGame = locationsGame;
        this.questionsGame = questionsGame;
    }

    // ACCESSORS

    public List<Location> getLocationsGame() {
        return locationsGame;
    }

    public void setLocationsGame(List<Location> locationsGame) {
        this.locationsGame = locationsGame;
    }

    public List<Question> getQuestionsGame() {
        return questionsGame;
    }

    public void setQuestionsGame(List<Question> questionsGame) {
        this.questionsGame = questionsGame;
    }


    // toString - ugly one


    @Override
    public String toString() {
        return "GameBoard{" +
                "locationsGame=" + locationsGame +
                ", questionsGame=" + questionsGame +
                '}';
    }
}
