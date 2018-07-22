package util;

import intf.constant.DefaultConstant;
import mapping.inside.Block;
import service.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageUtil implements DefaultConstant {

    public static Image spliceImage(Block list[][]) {
        BufferedImage NewImage = new BufferedImage(MAX_MAP_SIZE_WIDTH, MAX_MAP_SIZE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = NewImage.getGraphics();
        int i = 0, j = 0;
        BufferedImage cache;
        for(Block inside[] : list) {
            for(Block block : inside) {
                cache = AssetManager.getImage(block.getImageName());
                g.drawImage(cache, (i * DEFAULT_BLOCK_SIZE), (j * DEFAULT_BLOCK_SIZE), DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, null);
                j++;
            }
            j = 0;
            i++;
        }
        return NewImage;
    }

}
