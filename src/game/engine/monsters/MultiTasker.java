package game.engine.monsters;

import game.engine.Role;

public class MultiTasker extends Monster{
    private int normalSpeedTurns;

    public MultiTasker(String name, String description, Role Role, int energy) {
        super(name, description, Role, energy);
        this.normalSpeedTurns = 0;
    }

    public int getNormalSpeedTurns() {
        return normalSpeedTurns;
    }

    public void setNormalSpeedTurns(int normalSpeedTurns) {
        this.normalSpeedTurns = normalSpeedTurns;
    }
}
