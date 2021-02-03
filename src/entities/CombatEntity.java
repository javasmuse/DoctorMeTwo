package entities;

public abstract class CombatEntity {

    public int health;
    public int strength;

    public CombatEntity(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public abstract boolean attack(CombatEntity threat);

    public void deductHealth(int amount) {
        this.health -= amount;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }
}
