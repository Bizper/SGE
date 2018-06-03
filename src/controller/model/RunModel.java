package controller.model;

import impl.Player;
import mapping.MP;
import mapping.inside.Block;
import util.Log;

import java.util.HashMap;
import java.util.Map;

public class RunModel {

    private static Log log = Log.getInstance(RunModel.class);

    private Player currentPlayer;

    private MP currentMp;

    private Map<Integer, String> sentences = new HashMap<>();

    private int currentLocation[] = new int[]{0, 0};

    private int step = 0;

    public MP getCurrentMp() {
        return currentMp;
    }

    public RunModel setCurrentMp(MP currentMp) {
        this.currentMp = currentMp;
        return this;
    }

    public String getCurrentLocation() {
        return getBlock().getName();
    }

    public RunModel setMP(MP mp) {
        this.currentMp = mp;
        return this;
    }

    public RunModel setSentences(Map<Integer, String> sentences) {
        this.sentences = sentences;
        return this;
    }

    public String getWords() {
        return sentences.get(step);
    }

    public int[] getLocation() {
        return currentLocation;
    }

    public int getX() {
        return currentLocation[0];
    }

    public int getY() {
        return currentLocation[1];
    }

    public RunModel doNext() {
        step ++;
        return this;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public RunModel setLocation(int x, int y) {
        currentLocation[0] = x;
        currentLocation[1] = y;
        return this;
    }

    public int getStep() {
        return step;
    }

    public RunModel setStep(int step) {
        this.step = step;
        return this;
    }

    public Block getBlock() {
        return currentMp.get(currentLocation[0], currentLocation[1]);
    }

    public RunModel setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        return this;
    }

    @Override
    public String toString() {
        return "RunModel [" +
                "currentPlayer=" + currentPlayer +
                ", currentMp=" + currentMp +
                ", sentences=" + sentences +
                ", currentLocation=" + currentLocation +
                ", step=" + step +
                ']';
    }
}
