package application.controllers;

import application.model.*;

import java.util.ArrayList;

public class GameController {

    private GameFactory gameFactory;
    private Game game;

    public GameController() {
        this.gameFactory = new GameFactory();
        this.game = newGame();
    }

    public Game newGame() {
        Board board = gameFactory.createBoard();
        Player player1 = gameFactory.createHumanPlayer(Marker.X);
        Player player2 = gameFactory.createMiniMaxBot(Marker.O);
        Game game = gameFactory.createGame(board, player1, player2);
        return game;
    }

    public Move playMoveForHumanPlayer(int row, int col) {
        return game.playMoveForHumanPlayer(row, col);
    }

    public Move playMoveForBotPlayer() {
        return game.playMoveForBotPlayer();
    }

    public void switchPlayer() {
        game.switchPlayer();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public boolean isGameOver() {
        return game.getBoard().isGameOver();
    }

    public Marker hasWinner() {
        return game.getBoard().hasWinner();
    }

    public ArrayList<Move> getWinningMoves() {
        return game.getBoard().winningMoves();
    }

    public Marker getWinner() {
        return game.getBoard().getWinner();
    }

    public void resetGame() {
        game = newGame();
    }

    public Game getGame() {
        return game;
    }
}
