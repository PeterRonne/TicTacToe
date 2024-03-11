package application.controllers;

import application.model.*;

public class GameFactory {
    public Game createGame(Board board, Player player1, Player player2) {
        Game game = new Game(board, player1, player2);
        return game;
    }

    public Board createBoard() {
        Board board = new Board();
        return board;
    }

    public Player createHumanPlayer(Marker marker) {
        Player humanPlayer = new HumanPlayer(marker);
        return humanPlayer;
    }

    public Player createMiniMaxBot(Marker marker) {
        Player miniMaxBot = new MiniMaxBot(marker);
        return miniMaxBot;
    }

}
