package entities;

public class Virus extends Threat {

    private int dormantTime;

    public Virus(int health, int strength, int dormantTime) {
        super(health, strength);
        this.dormantTime = dormantTime;
    }

    @Override
    public void attack(Player player) {

    }
}
