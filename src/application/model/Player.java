package application.model;

import application.model.enums.Marker;
import application.model.enums.PlayerType;

public interface Player {
    public Move selectMove(Board board);

    public Marker getMarker();

    public PlayerType getType();
}
