package application.model;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
//    private Scanner scanner;
    private int[] selectedMove = new int[2];

    public HumanPlayer(Marker marker) {
        super(marker);
    }

    public void nextMove(int row, int col) {
        selectedMove[0] = row;
        selectedMove[1] = col;
    }

    @Override
    public Move selectMove(Board board) {
        Move choice = null;
        ArrayList<Move> candidates = board.getLegalMoves();
        int row = selectedMove[0];
        int col = selectedMove[1];
        for (Move candidate : candidates) {
            if (row == candidate.getRow() && col == candidate.getCol()) {
                choice = candidate;
                super.addSelectedMove(choice);
            }
        }
        return choice;
    }


//    ------------------------------------------------------------------------------//
//     for test purposses only
    public Move selectMoveTest(Board board) {
        Move choice = null;
        ArrayList<Move> candidates = board.getLegalMoves();
       Scanner scanner = new Scanner(System.in);
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


    @Override
    public String toString() {
        return "HumanPlayer " + super.getMarker();
    }
}
