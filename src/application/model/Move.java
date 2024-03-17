package application.model;

public class Move {
    private int row;
    private int col;
    private int value;
    private Marker marker;

    public Marker getMarker() {
        return marker;
    }

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = 0;
    }

    public Move(int row, int col, Marker marker) {
        this.row = row;
        this.col = col;
        this.marker = marker;
    }

    public int[] getMoveAsArray() {
        int[] move = new int[2];
        move[0] = getCol();
        move[1] = getRow();
        return move;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{row: " + row + " col:" + col + " " + marker + "}";
    }
}
