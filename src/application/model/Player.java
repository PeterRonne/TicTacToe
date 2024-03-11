package application.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private Marker marker;
    private List<Move> selectedMoves = new ArrayList<>();

    public Player(Marker marker) {
        this.marker = marker;
    }

    public abstract Move selectMove(Board board);

    public void addSelectedMove(Move move) {
        if (!selectedMoves.contains(move)) {
            selectedMoves.add(move);
        }
    }

    public Marker getMarker() {
        return marker;
    }
}
