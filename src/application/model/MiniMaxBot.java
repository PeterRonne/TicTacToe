package application.model;

import java.util.ArrayList;

public class MiniMaxBot extends Player {
    private final int MAX_DEPTH = 8;

    public MiniMaxBot(Marker marker) {
        super(marker);
    }

    @Override
    public Move selectMove(Board board) {
        boolean ismax = false;
        if (super.getMarker() == Marker.X)
            ismax = true;
        return minimax(board, ismax, super.getMarker(), 0);
    }

    private Move minimax(Board board, boolean isMax, Marker currentPlayer, int depth) {
        int boardEval;
        if (board.isGameOver() || depth == MAX_DEPTH) {
            boardEval = evaluate(board, depth);
            Move bestMove = board.lastMove();
            bestMove.setValue(boardEval);
            return bestMove;
        }

        // Otherwise, call minimax on each possible board combination
        ArrayList<Move> candidates = board.getLegalMoves();
        ArrayList<Move> candidateChoices = new ArrayList<>();

        for (Move candidate : candidates) {
            Board newBoard = new Board(board);
            newBoard.makeMove(candidate, currentPlayer);
            Move result = minimax(newBoard, !isMax, currentPlayer.other(), depth + 1);
            candidate.setValue(result.getValue());  // Set the value based on the result move
            candidateChoices.add(candidate);
        }

        // Determine which board combinations result in the best move for a particular agent
        Move bestChoice = null;
        int bestValue = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Move choice : candidateChoices) {
            if ((isMax && choice.getValue() > bestValue) || (!isMax && choice.getValue() < bestValue)) {
                bestChoice = choice;
                bestValue = choice.getValue();
            }
        }

        // Pick whichever move is the best for the particular agent
        return bestChoice;
    }

    private int evaluate(Board board, int depth) {
        Marker winner = board.hasWinner();
        int score;
        if (winner == Marker.X) {
            // Maximizing player wins
            score = 10 - depth;
        } else if (winner == Marker.O) {
            // Minimizing player wins
            score = -10 + depth;
        } else {
            // It's a tie or the game is still ongoing
            score = 0;
        }
        return score;
    }

}
