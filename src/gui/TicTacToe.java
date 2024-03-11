package gui;

import application.controllers.GameController;
import application.model.Move;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private GameController gameController = new GameController();
    private GridPane gameBoard;
    private Tile[][] tiles = new Tile[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);

        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initContent(BorderPane pane) {
        gameBoard = createGameBoard();
        pane.setCenter(gameBoard);
    }

    private GridPane createGameBoard() {
        GridPane gameBoard = new GridPane();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile(row, col, this::handleTileClick);
                gameBoard.add(tile, row, col);
                tiles[row][col] = tile;
            }
        }
        return gameBoard;
    }

    private void handleTileClick(ActionEvent actionEvent) {
        // Retrieve the source of the event, which should be the Tile button
        Tile clickedTile = (Tile) actionEvent.getSource();

        // Retrieve the row and column information from the clicked Tile
        int clickedCol = clickedTile.getCol();
        int clickedRow = clickedTile.getRow();

        Move playedMove = gameController.playMove(clickedRow, clickedCol);
        if (playedMove != null) {
            tiles[playedMove.getRow()][playedMove.getCol()].setMarker(gameController.getCurrentPlayer().getMarker());
        }

        System.out.println("Button clicked at row: " + clickedRow + ", column: " + clickedCol);
        gameController.switchPlayer();
    }


}
