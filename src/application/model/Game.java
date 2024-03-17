package application.model;

import java.util.ArrayList;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public Board getBoard() {
        return board;
    }

    public Move playMoveForBotPlayer() {
        Move move = currentPlayer.selectMove(board);
        if (move != null) {
            board.makeMove(move, currentPlayer.getMarker());
        }
        return move;
    }

    public Move playMoveForHumanPlayer(int row, int col) {
        Move move = null;
        if (currentPlayer instanceof HumanPlayer) {
            HumanPlayer humanPlayer = (HumanPlayer) currentPlayer;
            humanPlayer.nextMove(row, col);
            move = currentPlayer.selectMove(board);
            if (move != null)
                board.makeMove(move, currentPlayer.getMarker());
        }
        return move;
    }


    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void playGame() {
        while (!board.isGameOver()) {

            board.print();

            Move choice = currentPlayer.selectMove(board);
            board.makeMove(choice, currentPlayer.getMarker());

            if (board.getWinner() == currentPlayer.getMarker()) {
                board.print();
                System.out.println("Congratulations to player " + currentPlayer.getMarker() + " you win!!!");
            }

            switchPlayer();
        }
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}
