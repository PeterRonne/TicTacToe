package gui;

import application.controllers.GameController;
import application.model.Marker;
import application.model.Move;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class TicTacToe extends Application {

    private GameController gameController = new GameController();
    private GridPane gameBoard;
    private BorderPane pane;
    private Tile[][] tiles = new Tile[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);

        pane = new BorderPane();
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
        Tile clickedTile = (Tile) actionEvent.getSource();
        int clickedRow = clickedTile.getRow();
        int clickedCol = clickedTile.getCol();

        playMoveForHumanPlayer(clickedRow, clickedCol);

        // Start the animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {}));
        timeline.setOnFinished(event -> {
            Platform.runLater(() -> playAiAfterAnimation());
        });
        timeline.play();
    }

    private void playAiAfterAnimation() {
        playAi();
        if (gameController.isGameOver()) {
            displayGameOverMessage();
        }
    }

    private void displayGameOverMessage() {
        ButtonType newGameButtonType = new ButtonType("New Game", ButtonBar.ButtonData.OTHER);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.CANCEL, newGameButtonType);
        Marker winner = gameController.hasWinner();
        if (winner == Marker.X) {
            alert.setContentText("X wins!");
        } else if (winner == Marker.O) {
            alert.setContentText("O Wins!");
        } else if (winner == null) {
            alert.setContentText("Draw");
        }
        ButtonType cancelButtonType = ButtonType.CANCEL;
        Button cancel = (Button) alert.getDialogPane().lookupButton(cancelButtonType);
        cancel.setOnAction(null);

        Button newGameButton = (Button) alert.getDialogPane().lookupButton(newGameButtonType);
        newGameButton.setOnAction(event -> startNewGame());
        alert.showAndWait();
    }

    private void startNewGame() {
        gameController.resetGame();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tiles[row][col].resetTile(this::handleTileClick);
            }
        }
    }

    private void playAi() {
        if (!gameController.isGameOver()) {
            Move playedMove = gameController.playMoveForBotPlayer();
            if (playedMove != null) {
                tiles[playedMove.getRow()][playedMove.getCol()].setMarker(gameController.getCurrentPlayer().getMarker());
                if (gameController.hasWinner() != null) {
                    showWinningTiles();
                }
                gameController.switchPlayer();
            }
        }
    }

    private void playMoveForHumanPlayer(int clickedRow, int clickedCol) {
        if (!gameController.isGameOver()) {
            Move playedMove = gameController.playMoveForHumanPlayer(clickedRow, clickedCol);
            if (playedMove != null) {
                tiles[playedMove.getRow()][playedMove.getCol()].setMarker(gameController.getCurrentPlayer().getMarker());
                if (gameController.hasWinner() != null) {
                    showWinningTiles();
                }
                gameController.switchPlayer();
            }
        }
    }

    private void showWinningTiles() {
        ArrayList<Move> winningMoves = gameController.getWinningMoves();
        if (winningMoves != null) {
            for (Move winningMove : winningMoves) {
                tiles[winningMove.getRow()][winningMove.getCol()].setWinningStyle();
            }
        }
    }

}
