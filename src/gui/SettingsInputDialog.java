package gui;

import application.model.Player;
import application.model.enums.GameType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SettingsInputDialog extends Stage {

    public SettingsInputDialog(Stage owner) {
        initOwner(owner);
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private final ComboBox<Integer> boardSizes = new ComboBox<>();
    private int boardSize;
    private final ComboBox<GameType> gameTypes = new ComboBox<>();
    private final ArrayList<Player> players = new ArrayList<>();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(30));
        pane.setVgap(20);
        pane.setHgap(20);

        pane.add(new Label("Board Size"), 0, 0);

        Integer[] sizes = {3, 4, 5, 6};
        boardSizes.getItems().setAll(sizes);
        boardSizes.setPrefWidth(100);
        pane.add(boardSizes, 1, 0);

        pane.add(new Label("Game Type"), 0, 1);

        GameType[] types = {GameType.PVP, GameType.PVB, GameType.BVB};
        gameTypes.getItems().setAll(types);
        gameTypes.setPrefWidth(100);
        pane.add(gameTypes, 1, 1);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 2);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> this.cancelAction());
        Button btnOK = new Button("OK");
        buttonBox.getChildren().add(btnOK);
        btnOK.setOnAction(event -> this.okAction());
    }

    public int getBoardSize() {
        return boardSize;
    }

    private void okAction() {
        boardSize = (int) boardSizes.getValue();
        this.hide();
    }

    private void cancelAction() {
        this.hide();
    }

}
