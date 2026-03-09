package game.engine;

import game.engine.dataloader.DataLoader;
import game.engine.monsters.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final Board board;
    private final ArrayList<Monster> allMonsters;
    private final Monster player;
    private final Monster opponent;
    private Monster current;

    public Game (Role playerRole) throws IOException{
        this.board = new Board(DataLoader.readCards());
        this.allMonsters = DataLoader.readMonsters();

        this.player = selectRandomMonsterByRole(playerRole);
        Role oppRole = (playerRole == Role.SCARER) ? Role.LAUGHER : Role.SCARER;
        this.opponent = selectRandomMonsterByRole(oppRole);

        this.current = this.player;
    }

    private Monster selectRandomMonsterByRole(Role role) {
        ArrayList<Monster> candidates = new ArrayList<>();
        for (Monster m : allMonsters) {
            if (m.getRole() == role) {
                candidates.add(m);
            }
        }
        if(candidates.isEmpty())
            return null;
        return candidates.get(new Random().nextInt(candidates.size()));
    }

    public Board getBoard() { return board; }
    public ArrayList<Monster> getAllMonsters() { return allMonsters; }
    public Monster getPlayer() { return player; }
    public Monster getOpponent() { return opponent; }
    public Monster getCurrent() { return current; }
    public void setCurrent(Monster current) { this.current = current; }

}
