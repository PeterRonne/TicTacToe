package application.model;

import java.util.*;

public class Board {
    private int dimension = 3;
    private ArrayList<Move> playedMoves = new ArrayList<>();
//    private ArrayList<Move> legalMoves;
    private Marker[][] grid;
    private HashMap<Marker, Character> MarkerToChar = new HashMap<>();

    public Board() {
        this.grid = new Marker[dimension][dimension];
//        this.legalMoves = this.setLegalMoves();

        MarkerToChar.put(null, '.');
        MarkerToChar.put(Marker.X, 'X');
        MarkerToChar.put(Marker.O, 'O');
    }

    // Copy constructor for deep copy
    public Board(Board original) {
        this.dimension = original.dimension;

        // Deep copy for the grid
        this.grid = new Marker[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.grid[i][j] = original.grid[i][j];
            }
        }

        // Deep copy for the moves
        this.playedMoves = new ArrayList<>(original.playedMoves.size());
        for (Move move : original.playedMoves) {
            this.playedMoves.add(new Move(move.getRow(), move.getCol()));
        }
    }

    public Board deepCopy() {
        return new Board(this);
    }

    public void print() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                System.out.print(" " + MarkerToChar.get(grid[row][col]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Marker hasWinner() {
        // No winner can be found before (dimension * 2 - 1) moves have been played. That would be 5 moves if the boards dimension is 3.
        if (playedMoves.size() < dimension * 2 - 1) {
            return null;
        }

        // Check cols for win
        for (int row = 0; row < dimension; row++) {
            Set<Marker> uniqueCols = new HashSet<>(Arrays.asList(grid[row]));
            if (uniqueCols.size() == 1) {
                Marker marker = uniqueCols.iterator().next();

                if (marker != null) {
                    return marker;
                }
            }
        }

        // Check rows for win
        for (int col = 0; col < dimension; col++) {
            Set<Marker> uniqueRows = new HashSet<>();
            for (int row = 0; row < dimension; row++) {
                uniqueRows.add(grid[row][col]);
            }
            if (uniqueRows.size() == 1) {
                Marker marker = uniqueRows.iterator().next();
                if (marker != null) {
                    return marker;
                }
            }
        }
        // check backwards diagonal (top left to bottom right) for win
        Set<Marker> backwardsDiag = new HashSet<>();
        for (int i = 0; i < dimension; i++) {
            backwardsDiag.add(grid[i][i]);
        }

        if (backwardsDiag.size() == 1) {
            Marker marker = backwardsDiag.iterator().next();
            if (marker != null) {
                return marker;
            }
        }
        // check forwards diagonal (bottom left to top right) for win
        Set<Marker> forwardsDiag = new HashSet<>();
        for (int i = dimension - 1, j = 0; i >= 0 && j < dimension; i--, j++) {
            forwardsDiag.add(grid[i][j]);
        }

        if (forwardsDiag.size() == 1) {
            Marker marker = forwardsDiag.iterator().next();
            if (marker != null) {
                return marker;
            }
        }

        return null;
    }

    public boolean isGameOver() {
        if (hasWinner() != null || playedMoves.size() == dimension * dimension) {
            return true;
        } else {
            return false;
        }
    }

    public void makeMove(Move move, Marker marker) throws RuntimeException {
        if (isEmpty(move.getRow(), move.getCol())) {
            grid[move.getRow()][move.getCol()] = marker;
            playedMoves.add(move);
        } else {
            throw new RuntimeException("Attemting to move onto already occupied square");
        }
    }

    public ArrayList<Move> getLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (isEmpty(row, col)) {
                    legalMoves.add(new Move(row, col));
                }
            }
        }
        return legalMoves;
    }

    //** Maybe change to this, so the moves are only created once **//
    //** and then removed from the legal moves list once the move have been played **//
//    public ArrayList<Move> setLegalMoves() {
//        ArrayList<Move> legalMoves = new ArrayList<>();
//        for (int row = 0; row < dimension; row++) {
//            for (int col = 0; col < dimension; col++) {
//                if (isEmpty(row, col)) {
//                    legalMoves.add(new Move(row, col));
//                }
//            }
//        }
//        return legalMoves;
//    }

    public Marker getMarkAt(int row, int col) {
        return grid[row][col];
    }

    public Move lastMove() {
        return playedMoves.get(playedMoves.size() - 1);
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col] == null;
    }

    public Marker[][] getGrid() {
        return grid;
    }

    public ArrayList<Move> getPlayedMoves() {
        return playedMoves;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        reset();
    }

    public void reset() {
        this.grid = new Marker[dimension][dimension];
        this.playedMoves = new ArrayList<>();
    }
}
