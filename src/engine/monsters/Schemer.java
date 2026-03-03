package engine.monsters;

import engine.Role;

public class Schemer extends Monster{

    public Schemer(String name, String description, Role Role, int energy) {
        super(name, description, Role, energy);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
