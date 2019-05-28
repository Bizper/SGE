package service;

import controller.Exiter;
import intf.constant.DefaultConstant;
import resources.Strings;
import types.BlockType;
import util.Log;
import util.RandomUtil;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class AssetManager implements Strings {

    private static final HashMap<String, BufferedImage> images = new HashMap<>();
    private static final HashMap<String, AudioInputStream> audios = new HashMap<>();
    private static final HashMap<BlockType, String> blocks = new HashMap<>();
    private static final int MAX_LENGTH = 5;
    private static final Log log = Log.getInstance(AssetManager.class);

    public static void init() {
        log.log(loading_resource);
        load(DefaultConstant.ASSET_IMAGE_PATH);
        blocks.put(BlockType.TYPE_ROAD, "road_");
        blocks.put(BlockType.TYPE_GLASS, "glass_");
        blocks.put(BlockType.TYPE_BUILDING, "building_");
        blocks.put(BlockType.TYPE_WATER, "water_");
        blocks.put(BlockType.TYPE_WOOD, "wood_");
        log.log(loading_resource_complete);
    }

    private static void load(String path) {
        try {
            File dir = new File(path);
            if(!dir.exists()) {
                if(!dir.createNewFile()) {
                    log.error(initiate_asset_manager_error);
                    Exiter.exit();
                }
            }
            for(String s : Objects.requireNonNull(dir.list())) {
                File file = new File(path + s);
                if(file.isFile() && suffixCheck(s)) {
                    switch(getSuffix(s)) {
                        case "wav":
                            audios.put(replaceSuffix(file.getName()), AudioSystem.getAudioInputStream(file));
                            break;
                        default:
                            images.put(replaceSuffix(file.getName()), ImageIO.read(file));
                            break;
                    }

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
        return str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".wav");
    }

    private static String replaceSuffix(String str) {
        return str.split("\\.")[0];
    }

    private static String getSuffix(String str) {
        if(str.contains(".")) return str.split("\\.")[1];
        else return str;
    }

    public static BufferedImage getImage(String imageName) {
        if(imageName == null || imageName.isEmpty()) return null;
        return images.get(imageName);
    }

    public static String getImageName(BlockType type) {
        if(type == null) return "";
        return blocks.get(type) + RandomUtil.getInt(MAX_LENGTH);
    }

    public static AudioInputStream getAudio(String name) {
        if(name == null || name.isEmpty()) return null;
        return audios.get(name);
    }

}
