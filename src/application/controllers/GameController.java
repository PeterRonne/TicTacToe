package application.controllers;

import application.model.*;
import gui.TicTacToe;

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
        Player player2 = gameFactory.createHumanPlayer(Marker.O);
        Game game = gameFactory.createGame(board, player1, player2);
        return game;
    }

    public Move playMove(int row, int col) {
        return game.playMoveForCurrentPlayer(row, col);
    }


    public void switchPlayer() {
        game.switchPlayer();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }
}
