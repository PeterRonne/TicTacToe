package application.model;

import application.model.enums.GameState;

import java.util.ArrayList;

public class TicTacToe {
    private Board board;
    private GameState gameState;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public TicTacToe(Board board) {
        this.gameState = GameState.NOT_STARTED;
        this.board = board;
    }

    public void switchToNextPlayer() {
        for (Player player : players) {
            if (!currentPlayer.equals(player)) {
                currentPlayer = player;
            }
        }
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

    public Player getCurrentPlayer() {
        return currentPlayer;
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
