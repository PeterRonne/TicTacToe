package application.controllers;

import application.model.*;

import java.util.ArrayList;
import java.util.Map;

public class GameController {

    private GameFactory gameFactory;
    private Game game;

    public GameController() {
        this.gameFactory = new GameFactory();
        this.game = newGame("Human", Marker.X, "OneLayerBot", Marker.O);
    }

    public Game newGame(String playerOneType, Marker playerOneMarker, String playerTwoType, Marker playerTwoMarker) {
        Board board = gameFactory.createBoard();
        Player player1 = createPlayer(playerOneType, playerOneMarker);
        Player player2 = createPlayer(playerTwoType, playerTwoMarker);
        Game game = gameFactory.createGame(board, player1, player2);
        return game;
    }

    public Player createPlayer(String type, Marker marker) {
        Player player = null;
        switch (type) {
            case "Human":
                player = gameFactory.createHumanPlayer(marker);
                break;
            case "OneLayerBot":
                player = gameFactory.createOneLayerBot(marker);
                break;
            case "MiniMaxBot":
                player = gameFactory.createMiniMaxBot(marker);
                break;
            default:
                player = gameFactory.createHumanPlayer(marker);
        }
        return player;
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
//        game = newGame();
        game.getBoard().reset();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Move getBestMove() {
        return game.getBestMove();
    }
}
