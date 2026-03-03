package engine.monsters;

import engine.Role;

public class MultiTasker extends Monster{
    int normalSpeedTurns;

    public MultiTasker(String name, String description, Role Role, int energy) {
        super(name, description, Role, energy);
        this.normalSpeedTurns = 0;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
