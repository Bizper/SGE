package util;

import intf.constant.DefaultConstant;
import mapping.inside.Block;
import resources.Strings;
import service.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil implements DefaultConstant, Strings {

    private static Log log = Log.getInstance(ImageUtil.class);

    public static Image spliceImage(Block list[][]) {
        CallCheck.checkCaller();
        log.log(merge_image);
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
        log.log(merge_image_complete);
        return NewImage;
    }
}
