package entities;

public abstract class Threat {

    public int health;
    public int strength;

    public Threat(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public abstract void attack(Player player);

    public void deductHealth(int amount){
        this.health -= amount;
    }


}
