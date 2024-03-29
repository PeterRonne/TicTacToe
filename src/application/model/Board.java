package application.model;

import java.util.*;

public class Board {
    private int dimension = 3;
    private ArrayList<Move> playedMoves = new ArrayList<>();
    private Marker[][] grid;
    private Marker winner = null;
    private HashMap<Marker, Character> MarkerToChar = new HashMap<>();

    public Board() {
        this.grid = new Marker[dimension][dimension];
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
        return hasWinner() != null || playedMoves.size() == dimension * dimension;
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

    public Marker getMarkAt(int row, int col) {
        return grid[row][col];
    }

    public Move lastMove() {
        return playedMoves.get(playedMoves.size() - 1);
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col] == null;
    }

    public ArrayList<Move> getPlayedMoves() {
        return playedMoves;
    }

    public void reset() {
        this.grid = new Marker[dimension][dimension];
        this.playedMoves = new ArrayList<>();
    }

    public ArrayList<Move> winningMoves() {
        // No winner can be found before (dimension * 2 - 1) moves have been played. That would be 5 moves if the boards dimension is 3.
        if (playedMoves.size() < dimension * 2 - 1) {
            return null;
        }

        // winning row
        for (int row = 0; row < dimension; row++) {
            ArrayList<Move> winningRow = new ArrayList<>();
            for (int col = 0; col < dimension; col++) {
                winningRow.add(new Move(row, col, this.getMarkAt(row, col)));
            }
            Marker first = winningRow.get(0).getMarker();
            boolean allMatch = winningRow.stream().allMatch(move -> move.getMarker() == first);

            if (allMatch && first != null) {
                winner = first;
                return winningRow;
            }
        }

        // Winning column
        for (int col = 0; col < dimension; col++) {
            ArrayList<Move> winningCol = new ArrayList<>();
            for (int row = 0; row < dimension; row++) {
                winningCol.add(new Move(row, col, this.getMarkAt(row, col)));
            }
            Marker first = winningCol.get(0).getMarker();
            boolean allMatch = winningCol.stream().allMatch(move -> move.getMarker() == first);

            if (allMatch && first != null) {
                winner = first;
                return winningCol;
            }
        }

        // check backwards diagonal (top left to bottom right) for win
        ArrayList<Move> BackwardsDiag = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            BackwardsDiag.add(new Move(i, i, this.getMarkAt(i, i)));
        }

        Marker first = BackwardsDiag.get(0).getMarker();
        boolean allMatch = BackwardsDiag.stream().allMatch(move -> move.getMarker() == first);

        if (allMatch && first != null) {
            winner = first;
            return BackwardsDiag;
        }

        // check forwards diagonal (bottom left to top right) for win
        ArrayList<Move> forwardsDiag = new ArrayList<>();
        for (int i = dimension - 1, j = 0; i >= 0 && j < dimension; i--, j++) {
            forwardsDiag.add(new Move(i, j, this.getMarkAt(i, j)));
        }

        Marker firstInForwardDiag = forwardsDiag.get(0).getMarker();
        allMatch = forwardsDiag.stream().allMatch(move -> move.getMarker() == firstInForwardDiag);

        if (allMatch && firstInForwardDiag != null) {
            winner = firstInForwardDiag;
            return forwardsDiag;
        }

        return null;
    }

    public Marker getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "Board = " + dimension + " X " + dimension;
    }
}
