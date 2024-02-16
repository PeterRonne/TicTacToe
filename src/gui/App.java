package gui;
import application.controller.Controller;
import javafx.application.Application;

public class App {
    public static void main(String[] args) {
//        Game game = new Game(10);
//        Player xBot = new HumanPlayer(Marker.X);
//        Player oBot = new MiniMaxBot(Marker.O);
//        game.simulate(xBot, oBot,3, true);
        Controller.initStorage();
        Application.launch(TicTacToe.class);

    }
}
