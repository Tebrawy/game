package engine.monsters;

import engine.Role;

public class Dynamo extends Monster{
    public Dynamo(String name, String description, Role role, int energy) {
        super(name, description, role, energy);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
