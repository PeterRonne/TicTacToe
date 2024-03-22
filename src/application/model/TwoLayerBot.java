package application.model;

import application.model.Marker;

import java.util.ArrayList;
import java.util.Random;

public class TwoLayerBot extends Player {
    private Random random;

    public TwoLayerBot(Marker marker) {
        super(marker);
        this.random = new Random();
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();
        Move moveToBlock = null;

        for (Move candidate : candidates) {
            Board layerOne = board.deepCopy();
            layerOne.makeMove(candidate, super.getMarker());

            if (layerOne.hasWinner() == super.getMarker()) {
                return candidate;
            }

            ArrayList<Move> secondCandidates = layerOne.getLegalMoves();
            for (Move secondCandidate : secondCandidates) {
                Board LayerTwo = layerOne.deepCopy();
                LayerTwo.makeMove(secondCandidate, super.getMarker().other());

                if (LayerTwo.hasWinner() == super.getMarker().other()) {
                    moveToBlock = secondCandidate;
                }
            }
        }

        if (moveToBlock != null) {
            return moveToBlock;
        }

        return candidates.get(random.nextInt(candidates.size()));
    }

    @Override
    public String toString() {
        return "TwoLayerBot " + super.getMarker();
    }
}