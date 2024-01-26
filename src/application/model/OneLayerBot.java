package application.model;

import application.model.enums.Marker;
import application.model.enums.PlayerType;

import java.util.ArrayList;
import java.util.Random;

public class OneLayerBot implements Player {
    private Random random;
    private Marker marker;
    private PlayerType playerType;

    public OneLayerBot(Marker marker) {
        this.marker = marker;
        this.random = new Random();
        this.playerType = PlayerType.BOT;
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();

        for (Move candidate : candidates) {
            Board newBoard = board.deepCopy();
            newBoard.makeMove(candidate, marker);
            if (newBoard.hasWinner() == marker) {
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
    public PlayerType getType() {
        return playerType;
    }
}