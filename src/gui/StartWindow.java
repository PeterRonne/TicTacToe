package gui;

import application.model.*;
import application.model.enums.GameState;
import application.model.enums.Marker;
import application.model.enums.PlayerType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import storage.Storage;

import java.util.ArrayList;

public class StartWindow extends Application {
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

    private GameBoard guiBoard;
    private GameState gameState = GameState.ONGOING;
    private Board board = new Board(3);
    private MenuBar menuBar;
    private CheckMenuItem suggestMoves;
    private HumanPlayer humanPlayer = new HumanPlayer(Marker.X);
    private ArrayList<Player> bots = Storage.getBots();
    private Player botPlayer = bots.get(0);
    private MiniMaxBot helper = new MiniMaxBot(Marker.X);
    private Player currentPlayer = humanPlayer;

    private void initContent(BorderPane pane) {
        pane.setPadding(new Insets(10));
        initMenuBar();
        pane.setTop(menuBar);

        guiBoard = new GameBoard(3, this::handleTileClick);  // Pass the method reference
        guiBoard.initialize();
        pane.setRight(guiBoard);
    }

    private void handleTileClick(Tile tile) {
        if (currentPlayer != null && currentPlayer.getType() == PlayerType.HUMAN && gameState == GameState.ONGOING) {
            humanPlayer.setNextMove(new Move(tile.getRow(), tile.getCol()));
            Move choice = humanPlayer.selectMove(board);

            board.makeMove(choice, humanPlayer.getMarker());
            guiBoard.makeMove(choice, humanPlayer.getMarker());

            updateGameState();
        }
        if (gameState == GameState.ONGOING) {
            switchPlayer();
            PlayAi();
        }
    }

    private void PlayAi() {
        if (currentPlayer != null && currentPlayer.getType() == PlayerType.BOT) {
            Move choice = botPlayer.selectMove(board);
            board.makeMove(choice, botPlayer.getMarker());

            guiBoard.makeMove(choice, botPlayer.getMarker());

            updateGameState();
        }
        if (gameState == GameState.ONGOING) {
            switchPlayer();
            if (suggestMoves.isSelected()) {
                suggestMove();
            }
        }
    }

    private void suggestMove() {
        Move choice = helper.selectMove(board);
        guiBoard.getTileAt(choice.getRow(), choice.getCol()).setHelperStyle();
    }

    private void resetGameBoard() {
        board.reset();
        guiBoard.initialize();
        this.gameState = GameState.ONGOING;
        this.currentPlayer = humanPlayer;
        if (suggestMoves.isSelected()) {
            suggestMove();
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.getType() == PlayerType.HUMAN ? botPlayer : humanPlayer;
    }

    private void updateGameState() {
        Marker winner = board.hasWinner();
        if (winner == Marker.X) {
            this.gameState = GameState.X_WINS;
            guiBoard.setWinningLine();
        } else if (winner == Marker.O) {
            guiBoard.setWinningLine();
            this.gameState = GameState.O_WINS;
        } else if (board.getLegalMoves().size() == 0) {
            this.gameState = GameState.TIE;
        }
    }

    public void initMenuBar() {
        // Menu 1 Opponents:
        Menu opponents = new Menu("Bots");
        // random bot
        MenuItem randomBot = new MenuItem("RadomBot");
        randomBot.setOnAction(event -> {
            this.botPlayer = bots.get(0);
            System.out.println(botPlayer);
        });
        opponents.getItems().add(randomBot);
        // onlayerbot bot
        MenuItem oneLayerBot = new MenuItem("OneLayerBot");
        oneLayerBot.setOnAction(event -> {
            this.botPlayer = bots.get(1);
            System.out.println(botPlayer);
        });
        opponents.getItems().add(oneLayerBot);
        // twolayer bot
        MenuItem twoLayerBot = new MenuItem("TwoLayerBot");
        twoLayerBot.setOnAction(event -> {
            this.botPlayer = bots.get(2);
            System.out.println(botPlayer);
        });
        opponents.getItems().add(twoLayerBot);
        // minimax bot
        MenuItem minimaxBot = new MenuItem("MiniMaxBot");
        minimaxBot.setOnAction(event -> {
            this.botPlayer = bots.get(3);
            System.out.println(botPlayer);
        });
        opponents.getItems().add(minimaxBot);

        // Menu 2
        Menu gameControl = new Menu("Game Control");
        MenuItem startGame = new MenuItem("New Game");
        startGame.setOnAction(event -> {
            resetGameBoard();
        });
        gameControl.getItems().add(startGame);

        suggestMoves = new CheckMenuItem("Suggest moves");
        suggestMoves.setOnAction(event -> {
            suggestMove();
        });
        gameControl.getItems().add(suggestMoves);

        menuBar = new MenuBar(opponents, gameControl);
    }
}
