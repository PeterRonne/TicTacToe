package application.model;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;
    private int[] nextMove;

    public HumanPlayer(Marker marker) {
        super(marker);

    }

    public void setNextMove(int[] move) {
        this.nextMove = move;
    }


    //------------------------------------------------------------------------------//
    // for test purposses only
    @Override
    public Move selectMove(Board board) {
        Move choice = null;
        ArrayList<Move> candidates = board.getLegalMoves();
        scanner = new Scanner(System.in);
        do {
            System.out.println("pick a col");
            int col = scanner.nextInt();

            System.out.println("pick a row");
            int row = scanner.nextInt();

            for (Move candidate : candidates) {
                if (col == candidate.getCol() && row == candidate.getRow()) {
                    choice = candidate;
                }
            }

        } while (choice == null);

        return choice;
    }
    //------------------------------------------------------------------------------//
//    @Override
//    public Move selectMove(Board board) {
//        Move choice = null;
//        ArrayList<Move> candidates = board.getLegalMoves();
//
//        int row = nextMove[0];
//        int col = nextMove[1];
//
//        for (Move candidate : candidates) {
//            if (candidate.getRow() == row && candidate.getCol() == col) {
//                choice = candidate;
//            }
//        }
//        return choice;
//    }
}
