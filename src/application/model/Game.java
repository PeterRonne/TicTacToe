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
        Move move = null;
        if (!(currentPlayer instanceof HumanPlayer)) {
            move = currentPlayer.selectMove(board);
            if (move != null) {
                board.makeMove(move, currentPlayer.getMarker());
            }
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

    public Move getBestMove() {
        Player miniMax = new MiniMaxBot(currentPlayer.getMarker());
        return miniMax.selectMove(board);
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
            Move choice = null;
            if (currentPlayer instanceof HumanPlayer) {
                HumanPlayer humanPlayer = (HumanPlayer) currentPlayer;
                choice = humanPlayer.selectMoveTest(board);
            } else {
                choice = currentPlayer.selectMove(board);
            }

            board.makeMove(choice, currentPlayer.getMarker());

            if (board.hasWinner() == currentPlayer.getMarker()) {
                System.out.println("Congratulations to player " + currentPlayer.getMarker() + " you win!!!");
            }

            switchPlayer();
        }
        if (board.hasWinner() == null)
            System.out.println("It's a draw...");
        board.print();
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    @Override
    public String toString() {
        return board + " " + player1 + " " + player2;
    }
}
