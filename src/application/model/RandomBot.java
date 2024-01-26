package application.model;

import application.model.enums.Marker;
import application.model.enums.PlayerType;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot implements Player {
    private Random random;
    private Marker marker;
    private PlayerType playerType;

    public RandomBot(Marker marker) {
        this.marker = marker;
        this.random = new Random();
        this.playerType = PlayerType.BOT;
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();
        return candidates.get(random.nextInt(candidates.size()));
    }

    public Marker getMarker() {
        return marker;
    }

    @Override
    public PlayerType getType() {
        return playerType;
    }
}
