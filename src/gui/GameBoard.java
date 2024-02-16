package gui;

import application.model.enums.Marker;
import application.model.Move;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.util.*;
import java.util.function.Consumer;

public class GameBoard extends GridPane {
    private Tile[][] tiles;
    private int dimension;
    private Tile lastClicked;
    private HashMap<Marker, Character> MarkerToChar = new HashMap<>();
    private Consumer<Tile> tileClickHandler;  // Added a handler for tile click


    public GameBoard(int dimension, Consumer<Tile> tileClickHandler) {

        MarkerToChar.put(null, ' ');
        MarkerToChar.put(Marker.X, 'X');
        MarkerToChar.put(Marker.O, 'O');
        this.tileClickHandler = tileClickHandler;  // Initialize tileClickHandler
    }

    public void initialize(int dimension) {
        this.dimension = dimension;
        this.tiles = new Tile[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Tile tile = createTile(row, col);
                tile.setText(MarkerToChar.get(tile.getMarker()) + "");
                tiles[row][col] = tile;
                this.add(tile, row, col);
            }
        }
    }

    private Tile createTile(int row, int col) {
        Tile tile = new Tile(row, col);
        tile.setPrefSize(Tile.TILE_SIZE, Tile.TILE_SIZE);
        tile.setTextAlignment(TextAlignment.CENTER);
        tile.setBaseStyle();

        // Add an event handler to the button
        tile.setOnAction(event -> handleTileClick(tile));

        return tile;
    }

    public void handleTileClick(Tile tile) {
        lastClicked = tile;
        // Call the provided tile click handler
        tileClickHandler.accept(tile);
    }


    public void makeMove(Move move, Marker marker) {
        if (isEmpty(move.getRow(), move.getCol())) {
            tiles[move.getRow()][move.getCol()].update(marker);

        } else {
            throw new RuntimeException("Attemting to move onto already occupied square");
        }
    }

    public ArrayList<Tile> getWinningTiles() {

        // Check cols for win
        for (int row = 0; row < dimension; row++) {
            ArrayList<Tile> uniqueCol = new ArrayList<>(Arrays.asList(tiles[row]));
            Tile firstTile = uniqueCol.get(0);
            if (firstTile.getMarker() != null) {
                // Check if all tiles in the row have the same marker
                boolean isWinningCol = uniqueCol.stream()
                        .allMatch(tile -> tile.getMarker() == firstTile.getMarker());

                if (isWinningCol) {
                    // This row is a winning row
                    return uniqueCol;
                }
            }
        }

        // Check row for win
        for (int col = 0; col < dimension; col++) {
            ArrayList<Tile> uniqueRow = new ArrayList<>();
            for (int row = 0; row < dimension; row++) {
                uniqueRow.add(tiles[row][col]);
            }
            Tile firstTile = uniqueRow.get(0);
            if (firstTile.getMarker() != null) {
                // Check if all tiles in the row have the same marker
                boolean isWinningRow = uniqueRow.stream()
                        .allMatch(tile -> tile.getMarker() == firstTile.getMarker());

                if (isWinningRow) {
                    // This row is a winning row
                    return uniqueRow;
                }
            }
        }

        // check backwards diagonal (top left to bottom right) for win
        ArrayList<Tile> backwardsDiag = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            backwardsDiag.add(tiles[i][i]);
        }
        Tile firstTileBackwardDiag = backwardsDiag.get(0);
        if (firstTileBackwardDiag.getMarker() != null) {

            boolean isWinningBackwardsDiag = backwardsDiag.stream()
                    .allMatch(tile -> tile.getMarker() == firstTileBackwardDiag.getMarker());

            if (isWinningBackwardsDiag) {
                // This row is a winning row
                return backwardsDiag;
            }
        }

        // check forwards diagonal (bottom left to top right) for win
        ArrayList<Tile> forwardsDiag = new ArrayList<>();
        for (int i = dimension - 1, j = 0; i >= 0 && j < dimension; i--, j++) {
            forwardsDiag.add(tiles[i][j]);
        }

        Tile firstTileForwardDiag = forwardsDiag.get(0);
        if (firstTileForwardDiag.getMarker() != null) {

            boolean isWinningforwardsDiag = forwardsDiag.stream()
                    .allMatch(tile -> tile.getMarker() == firstTileForwardDiag.getMarker());

            if (isWinningforwardsDiag) {
                // This row is a winning row
                return forwardsDiag;
            }
        }
        return null;
    }

    public void setWinningLine() {

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                tiles[row][col].setBaseStyle();
            }
        }

        ArrayList<Tile> winningTiles = getWinningTiles();
        for (Tile winningTile : winningTiles) {
            winningTile.setWinningStyle();
        }
    }

    public boolean isEmpty(int row, int col) {
        return tiles[row][col].getMarker() == null;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        this.tiles = new Tile[dimension][dimension];

    }

    public Tile getLastClicked() {
        return lastClicked;
    }

    public Tile getTileAt(int row, int col) {
        return tiles[row][col];
    }


}
