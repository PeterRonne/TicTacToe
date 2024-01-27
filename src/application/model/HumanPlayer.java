package application.model;

import application.model.enums.Marker;
import application.model.enums.PlayerType;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Marker marker;
    private Scanner scanner;
    private PlayerType playerType;
    private int[] nextMove;

    public HumanPlayer(Marker marker) {
        this.marker = marker;
        this.scanner = new Scanner(System.in);
        this.playerType = PlayerType.HUMAN;
    }

    public void setNextMove(int[] move) {
        this.nextMove = move;
    }

    @Override
    public Move selectMove(Board board) {
        Move choice = null;
        ArrayList<Move> candidates = board.getLegalMoves();

        int row = nextMove[0];
        int col = nextMove[1];

        for (Move candidate : candidates) {
            if (candidate.getRow() == row && candidate.getCol() == col) {
                choice = candidate;
            }
        }
        return choice;
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public PlayerType getType() {
        return playerType;
    }


}
