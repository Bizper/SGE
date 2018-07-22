package controller.model;

import impl.Player;
import impl.person.Character;
import mapping.MP;
import mapping.inside.Block;
import util.ImageUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static intf.constant.DefaultConstant.FRAME_PER_SECOND;

public class RunModel {

    private static RunModel runModel;

    private Player currentPlayer;

    private Character currentCharacter;

    private long time = 0L;

    private MP currentMp;

    private Image map;

    private int map_hash;

    private Map<Integer, String> sentences = new HashMap<>();

    private int currentLocation[] = new int[]{0, 0};

    private int step = 0;

    private boolean move_up = false;

    private boolean move_left = false;

    private boolean move_right = false;

    private boolean move_down = false;

    public RunModel setMove_up(boolean move_up) {
        this.move_up = move_up;
        return this;
    }

    public RunModel setMove_left(boolean move_left) {
        this.move_left = move_left;
        return this;
    }

    public RunModel setMove_right(boolean move_right) {
        this.move_right = move_right;
        return this;
    }

    public RunModel setMove_down(boolean move_down) {
        this.move_down = move_down;
        return this;
    }

    private RunModel() {}

    public static RunModel getInstance() {
        if(runModel == null) runModel = new RunModel();
        return runModel;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public RunModel setCurrentCharacter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
        setLocation(currentCharacter.getLocation()[0], currentCharacter.getLocation()[1]);
        return this;
    }

    public MP getCurrentMp() {
        return currentMp;
    }

    public Image getBackground() {
        return currentMp.getBackground();
    }

    public Image getMap() {
        return map;
    }

    public RunModel setMP(MP mp) {
        this.currentMp = mp;
        if(map_hash == 0 || map_hash != mp.hashCode()) {
            map = ImageUtil.spliceImage(mp.getList());
            map_hash = currentMp.hashCode();
        }
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
        currentCharacter.setLocation(x, y);
        return this;
    }

    public RunModel setX(int x) {
        currentLocation[0] = x;
        currentCharacter.setLocation(x, currentCharacter.getLocation()[1]);
        return this;
    }

    public RunModel setY(int y) {
        currentLocation[1] = y;
        currentCharacter.setLocation(currentCharacter.getLocation()[0], y);
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

    public RunModel checkStatus() {
        if(move_up) moveUp();
        if(move_down) moveDown();
        if(move_left) moveLeft();
        if(move_right) moveRight();
        return this;
    }

    public RunModel moveUp() {
        setY(getY() - (currentCharacter.getMoveSpeed() / FRAME_PER_SECOND));
        return this;
    }

    public RunModel moveLeft() {
        setX(getX() - (currentCharacter.getMoveSpeed() / FRAME_PER_SECOND));
        return this;
    }

    public RunModel moveDown() {
        setY(getY() + (currentCharacter.getMoveSpeed() / FRAME_PER_SECOND));
        return this;
    }

    public RunModel moveRight() {
        setX(getX() + (currentCharacter.getMoveSpeed() / FRAME_PER_SECOND));
        return this;
    }

    @Override
    public String toString() {
        return "RunModel [" +
                "currentPlayer=" + currentPlayer +
                ", currentCharacter=" + currentCharacter +
                ", time=" + time +
                ", currentMp=" + currentMp +
                ", map=" + map +
                ", map_hash=" + map_hash +
                ", sentences=" + sentences +
                ", currentLocation=" + Arrays.toString(currentLocation) +
                ", step=" + step +
                ']';
    }
}
