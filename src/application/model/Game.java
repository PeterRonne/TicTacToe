package application.model;

import application.model.enums.GameState;

import java.util.ArrayList;

public class Game {
    private Board board;
    private GameState gameState;
    private final ArrayList<Player> players = new ArrayList<>();

    public Game(Board board) {
        this.gameState = GameState.NOT_STARTED;
        this.board = board;
    }


    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public int getBoardDimension() {
        return board.getDimension();
    }

    public Board getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


}
