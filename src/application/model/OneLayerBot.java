package application.model;


import java.util.ArrayList;
import java.util.Random;

public class OneLayerBot extends Player {
    private Random random;
    private Marker marker;

    public OneLayerBot(Marker marker) {
        super(marker);
        this.marker = marker;
        this.random = new Random();
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();

        for (Move candidate : candidates) {
            Board newBoard = board.deepCopy();
            newBoard.makeMove(candidate, marker);
            if (newBoard.getWinner() == marker) {
                return candidate;
            }
        }
        return candidates.get(random.nextInt(candidates.size()));
    }

    @Override
    public Marker getMarker() {
        return marker;
    }

    @Override
    public String toString() {
        return "OneLayerBot " + super.getMarker();
    }
}