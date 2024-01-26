package application.model;

import application.model.enums.Marker;

public class Game {
    private int numOfGames;
    private int xWins;
    private int oWins;
    private int ties;

    public Game(int numOfGames) {
        this.numOfGames = numOfGames;
        this.xWins = 0;
        this.oWins = 0;
        this.ties = 0;
    }

    public void simulate(Player xbot, Player obot, int boardDimension, boolean printgame) {
        for (int i = 0; i < numOfGames; i++) {
            Board board = new Board(boardDimension);

            Player currentPlayer = xbot;

            Marker winner = null;
            for (int j = 0; j < boardDimension * boardDimension; j++) {
                Move choice = currentPlayer.selectMove(board);

                board.makeMove(choice, currentPlayer.getMarker());

                winner = board.hasWinner();

                if (printgame) {
                    board.print();
                }
                if (winner != null) {
                    if (printgame)
                        System.out.println(currentPlayer.getMarker() + " won!");
                    break;
                } else if (j == boardDimension * boardDimension - 1) {
                    if (printgame)
                        System.out.println("its a tie...");
                    break;
                }
                currentPlayer = currentPlayer.getMarker().equals(Marker.X) ? obot : xbot;
            }
            if (printgame)
                System.out.println("------------------------------------------");
            if (winner == Marker.X) {
                xWins++;
            } else if (winner == Marker.O) {
                oWins++;
            } else {
                ties++;
            }
        }
        System.out.println("x wins: " + xWins);
        System.out.println("o wins: " + oWins);
        System.out.println("ties: " + ties);
    }



}
