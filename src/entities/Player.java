package entities;

import java.util.HashMap;

public class Player {

    private String name;
    private int health;
    private HashMap<Integer, Integer> pointsByGameId;

    public Player(String name, int health) {
        setName(name);
        setHealth(health);
        setPointsByGameId();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public boolean addPoints(int gameId, int newPoints) {
        // Look up game Id to get appropriate points
        int currPoints = getPointsByGameId(gameId);
        this.pointsByGameId.put(gameId, currPoints + newPoints);
        return true;
    }

    public int getPointsByGameId(int gameId) {
        // Look up game Id to get appropriate points
        return this.pointsByGameId.get(gameId);
    }

    private void setPointsByGameId() {
        this.pointsByGameId = new HashMap<Integer, Integer>();
    }
    @Override
    public String toString() {
        return "model.Player's name is: " + name;
    }
}