package entities;

public class Bacteria extends Threat {

    private int specialAttackAmount;

    public Bacteria(int health, int strength, int specialAttackAmount) {
        super(health, strength);
        this.specialAttackAmount = specialAttackAmount;
    }

    @Override
    public void attack(Player player) {

    }
}
