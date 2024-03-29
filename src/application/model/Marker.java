package application.model;

public enum Marker {
    X(1),
    O(2),
    EMPTY(0);

    private final int value;

    Marker(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Marker other() {
        return (this == O) ? X : O;
    }
}
