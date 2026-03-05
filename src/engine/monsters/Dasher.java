package engine.monsters;

import engine.Role;

public class Dasher extends Monster{
    int momentumTurns;

    public Dasher(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
        this.momentumTurns = 0;
    }
}
