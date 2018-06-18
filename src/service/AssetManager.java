package service;

import constant.BlockType;
import util.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

import static intf.DefaultConstant.PATH;

public class AssetManager {

    private static HashMap<String, Image> images = new HashMap<>();
    private static HashMap<BlockType, String> blocks = new HashMap<>();
    private static Log log = Log.getInstance(AssetManager.class);

    public static void init() {
        log.log("loading resources...");
        load(PATH);
        blocks.put(BlockType.TYPE_ROAD, "1");
        blocks.put(BlockType.TYPE_GLASS, "2");
        log.log("loading resources complete.");
    }

    private static void load(String path) {
        try {
            File dir = new File(path);
            if(!dir.exists()) dir.createNewFile();
            for(String s : dir.list()) {
                File file = new File(path + s);
                if(file.isFile() && suffixCheck(s)) {
                    images.put(replaceSuffix(file.getName()), ImageIO.read(file));
                }
                if(file.isDirectory()) {
                    load(file.getCanonicalPath() + "/");
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private static boolean suffixCheck(String str) {
        return str.endsWith(".jpg") || str.endsWith(".png");
    }

    private static String replaceSuffix(String str) {
        return str.split("\\.")[0];
    }

    public static Image getImage(String imageName) {
        return images.get(imageName);
    }

    public static Image getImage(BlockType type) {
        return images.get(blocks.get(type));
    }

}
