package application.controllers;

import application.model.*;

public class GameFactory {
    public static Game newGame(Board board, Player player1, Player player2) {
        Game game = new Game(board, player1, player2);
        return game;
    }

    public static Board CreateBoard() {
        Board board = new Board();
        return board;
    }

    public static Player createHumanPlayer(Marker marker) {
        Player humanPlayer = new HumanPlayer(marker);
        return humanPlayer;
    }

    public static Player createMiniMaxBot(Marker marker) {
        Player miniMaxBot = new MiniMaxBot(marker);
        return miniMaxBot;
    }

}
