package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {
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
        pane.setCenter(GameBoard());
    }

    private GridPane GameBoard() {
        GridPane gameBoard = new GridPane();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile(row, col, this::handleTileClick);
                gameBoard.add(tile, row, col);
            }
        }
        return gameBoard;
    }

    private void handleTileClick(ActionEvent actionEvent) {
        // Retrieve the source of the event, which should be the Tile button
        Tile clickedTile = (Tile) actionEvent.getSource();

        // Retrieve the row and column information from the clicked Tile
        int clickedRow = clickedTile.getRow();
        int clickedCol = clickedTile.getCol();

        System.out.println("Button clicked at row: " + clickedRow + ", column: " + clickedCol);
    }


}
