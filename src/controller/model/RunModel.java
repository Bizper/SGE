package controller.model;

import constant.BlockType;
import impl.Player;
import mapping.MP;
import mapping.inside.Block;
import util.DateUtil;
import util.Log;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RunModel {

    private static RunModel runModel;

    private static Log log = Log.getInstance(RunModel.class);

    private Player currentPlayer;

    private long time = 0L;

    private MP currentMp;

    private Map<Integer, String> sentences = new HashMap<>();

    private int currentLocation[] = new int[]{0, 0};

    private int step = 0;

    private RunModel() {}

    public static RunModel getInstance() {
        if(runModel == null) runModel = new RunModel();
        return runModel;
    }

    public MP getCurrentMp() {
        return currentMp;
    }

    public Image getBackground() {
        return currentMp.getBackground();
    }

    public RunModel setCurrentMp(MP currentMp) {
        this.currentMp = currentMp;
        return this;
    }

    public RunModel setMP(MP mp) {
        this.currentMp = mp;
        return this;
    }

    public RunModel setSentences(Map<Integer, String> sentences) {
        this.sentences = sentences;
        return this;
    }

    public long getTime() {
        return time;
    }

    public RunModel timePlus() {
        this.time += 1;
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

    public Block getBlock(int x, int y) {
        return currentMp.get(x, y);
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
