package application.model;

import java.util.ArrayList;

public class MiniMaxBot extends Player {

    public MiniMaxBot(Marker marker) {
        super(marker);
    }

    @Override
    public Move selectMove(Board board) {
        boolean ismax = false;
        if (super.getMarker() == Marker.X)
            ismax = true;

        MiniMax miniMaxThread = new MiniMax(board, ismax, super.getMarker());
        miniMaxThread.run();

        return miniMaxThread.getBestMove();
    }

    @Override
    public String toString() {
        return "MiniMaxBot " + super.getMarker() ;
    }
}
