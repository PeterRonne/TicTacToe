package gui;

import application.model.Marker;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsWindow extends Stage {
    public SettingsWindow(Stage owner) {
        this.initOwner(owner);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);
        this.setTitle("Settings");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private boolean applyNewSettings = false;

    private Marker playerOneMarker;
    private Marker playerTwoMarker;

    private ComboBox<Marker> playerOneMarkers;
    private ComboBox<Marker> playerTwoMarkers;

    private String playerOneType;
    private String playerTwoType;

    private ComboBox<String> playerOneTypes;
    private ComboBox<String> playerTwoTypes;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(50);
        pane.setVgap(10);

        String Players[] = {"Human", "OneLayerBot", "MinimaxBot"};
        Marker markers[] = {Marker.X, Marker.O};

        //Player One
        Label lblPlayerOne = new Label("PlayerOne:");
        pane.add(lblPlayerOne, 0, 0);

        playerOneTypes = new ComboBox<>();
        playerOneTypes.getItems().addAll(Players);

        playerOneTypes.setPrefWidth(120);

        playerOneTypes.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                playerOneType = playerOneTypes.getSelectionModel().getSelectedItem();
            }
        }));

        playerOneTypes.getSelectionModel().select(0);
        pane.add(playerOneTypes, 0, 1);

        //-------------------------------------------------------------------//

        playerOneMarkers = new ComboBox<>();
        playerOneMarkers.setPrefWidth(55);
        playerOneMarkers.getItems().addAll(markers);

        playerOneMarkers.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                playerOneMarker = playerOneMarkers.getSelectionModel().getSelectedItem();
            }
        }));

        playerOneMarkers.getSelectionModel().selectFirst();
        pane.add(playerOneMarkers, 0, 2);

        //Player Two
        Label lblPlayerTwo = new Label("PlayerTwo:");
        pane.add(lblPlayerTwo, 1, 0);

        playerTwoTypes = new ComboBox<>();
        playerTwoTypes.getItems().addAll(Players);
        playerTwoTypes.setPrefWidth(120);

        playerTwoTypes.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                playerTwoType = playerTwoTypes.getSelectionModel().getSelectedItem();
            }
        }));

        playerTwoTypes.getSelectionModel().select(1);
        pane.add(playerTwoTypes, 1, 1);

        //-------------------------------------------------------------------//

        playerTwoMarkers = new ComboBox<>();
        playerTwoMarkers.getItems().addAll(markers);
        playerTwoMarkers.setPrefWidth(55);

        playerTwoMarkers.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                playerTwoMarker = playerTwoMarkers.getSelectionModel().getSelectedItem();
            }
        }));

        playerTwoMarkers.getSelectionModel().selectLast();
        pane.add(playerTwoMarkers, 1, 2);

        //-------------------------------------------------------------------//

        Button btnApply = new Button("Apply");
        pane.setHalignment(btnApply, HPos.LEFT);
        pane.add(btnApply, 0, 3);
        btnApply.setOnAction(event -> this.applySettingsAction());

        Button btnCancel = new Button("Cancel");
        pane.setHalignment(btnCancel, HPos.RIGHT);
        pane.add(btnCancel, 1, 3);
        btnCancel.setOnAction(event -> cancelAction());
    }

    private void cancelAction() {
        applyNewSettings = false;
        close();
    }


    private void applySettingsAction() {
        System.out.println("PlayerOne: " + playerOneType + " " + playerOneMarker);
        System.out.println("PlayerTwo: " + playerTwoType + " " + playerTwoMarker);

        if (!playerOneMarker.equals(playerTwoMarker)) {
            applyNewSettings = true;
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "PlayerOne and PlayerTwo have the same marker");
            alert.showAndWait();
        }
    }

    public boolean isApplyNewSettings() {
        return applyNewSettings;
    }

    public Marker getPlayerOneMarker() {
        return playerOneMarker;
    }

    public Marker getPlayerTwoMarker() {
        return playerTwoMarker;
    }

    public String getPlayerOneType() {
        return playerOneType;
    }

    public String getPlayerTwoType() {
        return playerTwoType;
    }
}
