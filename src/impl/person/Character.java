package impl.person;

import base.Instance;
import service.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Character extends Instance {

    private BufferedImage char_image = AssetManager.getImage("main");

    public BufferedImage getImage() {
        return char_image;
    }

    public Character setImage(BufferedImage char_image) {
        this.char_image = char_image;
        return this;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(char_image, x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT, null);
    }

    @Override
    public String toString() {
        return "Character [" +
                "hp=" + hp +
                ", maxHp=" + maxHp +
                ", mp=" + mp +
                ", maxMp=" + maxMp +
                ", attack=" + attack +
                ", defence=" + defence +
                ", default_type=" + default_type +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", moveSpeed=" + moveSpeed +
                ", owner=" + owner +
                ", pack_size=" + pack_size +
                ", x=" + x +
                ", y=" + y +
                ", spell_list=" + spell_list +
                ", pack=" + (pack == null ? null : Arrays.toString(pack)) +
                ", ID=" + ID +
                ", isUsed=" + isUsed +
                ']';
    }
}
