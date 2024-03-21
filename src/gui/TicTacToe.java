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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class TicTacToe extends Application {

    private GameController gameController = new GameController();
    private SettingsWindow settingsWindow;
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

        settingsWindow = new SettingsWindow(primaryStage);
    }

    private void initContent(BorderPane pane) {
        gameBoard = createGameBoard();
        gameBoard.setPadding(new Insets(10));
        gameBoard.setHgap(5);
        gameBoard.setVgap(5);
        gameBoard.setCache(true);
        pane.setCenter(gameBoard);

        Button showMove = new Button("Show move");
        showMove.setOnAction(event -> showMove());
        Button settings = new Button("Settings");
        settings.setOnAction(event -> openSettingsMenu());

        HBox menuBar = new HBox(85, showMove, new Region(), settings);

        menuBar.setPadding(new Insets(0, 10, 0, 10));
        menuBar.setAlignment(Pos.BASELINE_RIGHT);
        pane.setTop(menuBar);

        playAiWithDelay();
    }

    private void openSettingsMenu() {
        settingsWindow.showAndWait();

        if (settingsWindow.isApplyNewSettings()) {
            System.out.println("New settings have been applied");
        }
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
        playAiWithDelay();
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

    private void playAiWithDelay() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
        }));
        timeline.setOnFinished(event -> {
            Platform.runLater(() -> {
                playAi();
                if (gameController.isGameOver()) {
                    displayGameOverMessage();
                }
            });
        });
        timeline.play();
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
        playAiWithDelay();
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

    private void showMove() {
        if (!gameController.isGameOver()) {
            Move playedMove = gameController.getBestMove();
            if (playedMove != null) {
                tiles[playedMove.getRow()][playedMove.getCol()].setHelperStyle();
            }
        }
    }
}
