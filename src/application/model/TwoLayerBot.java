package application.model;

import application.model.enums.Marker;
import application.model.enums.PlayerType;

import java.util.ArrayList;
import java.util.Random;

public class TwoLayerBot implements Player {
    private Random random;
    private Marker marker;
    private PlayerType playerType;

    public TwoLayerBot(Marker marker) {
        this.marker = marker;
        this.random = new Random();
        this.playerType = PlayerType.BOT;
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();
        Move moveToBlock = null;

        for (Move candidate : candidates) {
            Board layerOne = board.deepCopy();
            layerOne.makeMove(candidate, marker);

            if (layerOne.hasWinner() == marker) {
                return candidate;
            }

            ArrayList<Move> secondCandidates = layerOne.getLegalMoves();
            for (Move secondCandidate : secondCandidates) {
                Board LayerTwo = layerOne.deepCopy();
                LayerTwo.makeMove(secondCandidate, marker.other());

                if (LayerTwo.hasWinner() == marker.other()) {
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
    public Marker getMarker() {
        return marker;
    }

    @Override
    public PlayerType getType() {
        return playerType;
    }
}