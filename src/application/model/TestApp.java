package application.model;

public class TestApp {
    public static void main(String[] args) {
        Board board = new Board();
        Player player1 = new HumanPlayer(Marker.X);
        Player player2 = new MiniMaxBot(Marker.O);

        Game game = new Game(board, player1, player2);

        game.playGame();
    }
}
