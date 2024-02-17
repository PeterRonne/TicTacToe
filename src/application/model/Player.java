package application.model;

public abstract class Player {
    private Marker marker;

    public Player(Marker marker) {
        this.marker = marker;
    }

    public abstract Move selectMove(Board board);

    public Marker getMarker() {
        return marker;
    }
}
