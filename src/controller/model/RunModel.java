package controller.model;

import impl.Player;

public class RunModel {

    private String currentLocation = "";

    private Player currentPlayer;

    public String getCurrentLocation() {
        return currentLocation;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public RunModel setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public RunModel setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        return this;
    }
}
