package application.model;

import application.model.Marker;


import java.util.ArrayList;
import java.util.Random;

public class RandomBot extends Player {
    private Random random;

    public RandomBot(Marker marker) {
        super(marker);
        this.random = new Random();
    }

    @Override
    public Move selectMove(Board board) {
        ArrayList<Move> candidates = board.getLegalMoves();
        return candidates.get(random.nextInt(candidates.size()));
    }
}
