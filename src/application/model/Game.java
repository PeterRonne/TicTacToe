package application.model;

import java.util.ArrayList;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Board board,Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public Board getBoard() {
        return board;
    }

    public void playGame() {
        while (!board.isGameOver()) {

            board.print();

            Move choice = currentPlayer.selectMove(board);
            board.makeMove(choice, currentPlayer.getMarker());

            if (board.hasWinner() == currentPlayer.getMarker()) {
                board.print();
                System.out.println("Congratulations to player " + currentPlayer.getMarker() + " you win!!!");
            }

            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


}
