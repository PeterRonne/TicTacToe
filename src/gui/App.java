package gui;
import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

public class App {
    public static void main(String[] args) {
//        Game game = new Game(10);
//        Player xBot = new HumanPlayer(Marker.X);
//        Player oBot = new MiniMaxBot(Marker.O);
//        game.simulate(xBot, oBot,3, true);
        Controller.initStorage();
        Application.launch(StartWindow.class);

    }
}
