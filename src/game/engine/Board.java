package game.engine;

import game.engine.cards.Card;
import game.engine.cells.Cell;
import game.engine.monsters.Monster;

import java.util.ArrayList;

public class Board {
    private final Cell[][] boardCell;
    private static ArrayList<Monster> stationedMonsters;
    private final ArrayList<Card> originalCards;
    private static ArrayList<Card> cards;

    public Board(ArrayList<Card> readCards) {
        this.originalCards = readCards;
        this.boardCell = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
        stationedMonsters = new ArrayList<>();
        cards = new ArrayList<>();
    }

    public Cell[][] getBoardCell() {
        return boardCell;
    }

    public static ArrayList<Monster> getStationedMonsters() {
        return stationedMonsters;
    }

    public ArrayList<Card> getOriginalCards() {
        return originalCards;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public static void setStationedMonsters(ArrayList<Monster> monsters) {
        stationedMonsters = monsters;
    }

    public static void setCards(ArrayList<Card> newCards) {
        cards = newCards;
    }
}
