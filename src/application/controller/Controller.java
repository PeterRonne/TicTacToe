package application.controller;

import application.model.*;
import application.model.enums.Marker;
import storage.Storage;

public class Controller {

//    public static Board createBoard(int dimension) {
//        return new Board(dimension);
//    }
//    public static TicTacToe createGame(int dimension) {
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(new HumanPlayer(Marker.X));
//        players.add(new RandomBot(Marker.O));
//
//        TicTacToe ticTacToe = new TicTacToe(createBoard(dimension));
//
//        return ticTacToe;
//    }


    public static void initStorage() {
        Player bot1 = new RandomBot(Marker.O);
        Player bot2 = new OneLayerBot(Marker.O);
        Player bot3 = new TwoLayerBot(Marker.O);
        Player bot4 = new MiniMaxBot(Marker.O);

        Storage.addBot(bot1);
        Storage.addBot(bot2);
        Storage.addBot(bot3);
        Storage.addBot(bot4);
    }
}
