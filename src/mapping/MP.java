package mapping;

import intf.DefaultConstant;
import mapping.inside.Block;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 地图文件的映射
 */
public class MP implements DefaultConstant {

    private String name;

    private Image background;

    private Block list[][];

    public MP(String name, Block[][] list) {
        this.name = name;
        this.list = list;
    }

    public Image getBackground() {
        return background;
    }

    public MP setBackground(Image background) {
        this.background = background;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Block get(int x, int y) {
        return list[x][y];
    }

    @Override
    public String toString() {
        return "MP [" +
                "name='" + name + '\'' +
                ", background=" + background +
                ", list=" + (list == null ? null : Arrays.asList(list)) +
                ']';
    }
}
