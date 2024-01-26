package application.model;

public class Move {
    private int row;
    private int col;
    private int value;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = 0;
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
}
