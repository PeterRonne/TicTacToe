package gui;

import application.model.Marker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Tile extends Button {
    public static final int TILE_SIZE = 100;
    private int row;
    private int col;
    private Marker marker;

    public Tile(int row, int col, EventHandler<ActionEvent> action) {
        this.row = row;
        this.col = col;
        this.marker = null;
        this.setPrefSize(TILE_SIZE, TILE_SIZE);
        setBaseStyle();
        this.setOnAction(action);
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
        this.setText("" + marker);
        this.setOnAction(null);
    }

    public void resetTile(EventHandler<ActionEvent> action) {
        this.marker = null;
        this.setText("");
        this.setPrefSize(TILE_SIZE, TILE_SIZE);
        setBaseStyle();
        this.setOnAction(action);
    }

    public void setBaseStyle() {
        this.setStyle("-fx-font-size:45; -fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 0px;");
    }

    public void setWinningStyle() {
        this.setStyle("-fx-font-size:45; -fx-background-color: #7AE582; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 0px;");
    }
//
//    public void setHelperStyle() {
//        this.setStyle("-fx-font-size:45; -fx-background-color: #00A5CF; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 0px;");
//    }

    public Marker getMarker() {
        return marker;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "{" + row + ", " + col + "}";
    }

}
