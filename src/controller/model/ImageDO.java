package controller.model;

import java.awt.*;

public class ImageDO {

    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;

    public ImageDO setImage(Image image) {
        this.image = image;
        return this;
    }

    public ImageDO setX(int x) {
        this.x = x;
        return this;
    }

    public ImageDO setY(int y) {
        this.y = y;
        return this;
    }

    public ImageDO setWidth(int width) {
        this.width = width;
        return this;
    }

    public ImageDO setHeight(int height) {
        this.height = height;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
