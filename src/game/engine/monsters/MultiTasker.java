package game.engine.monsters;

import game.engine.Role;

public class MultiTasker extends Monster{
    int normalSpeedTurns;

    public MultiTasker(String name, String description, Role Role, int energy) {
        super(name, description, Role, energy);
        this.normalSpeedTurns = 0;
    }
}
