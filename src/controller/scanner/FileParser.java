package controller.scanner;

import base.Instance;
import constant.DefaultConstant;
import mapping.*;
import mapping.inside.Block;
import util.Log;

import java.io.*;

public class FileParser {

    private static Log log = Log.getInstance(FileParser.class);

    private static double MIN_PROGRESS = 0.0;
    private static double MAX_PROGRESS = 100.0;

    private static BufferedReader br;

    private static double progress = MIN_PROGRESS;

    public static BufferedReader init(File f) {
        try {
            return new BufferedReader(new FileReader(f));
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }

    public static SCE parseSCE(String path) {
        SCE sce = new SCE();
        File file = new File(path);
        if(!path.endsWith(".sce")) {
            log.error("not current SCE file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String cache;
        br = init(file);
        if(br == null) return sce;
        try {
            while((cache = br.readLine()) != null) {
                sce = parseSCE(cache, sce);
            }
        } catch (IOException e) {
            log.error(e);
        }
        setProgress(MAX_PROGRESS);
        return sce;
    }

    public static PLR parsePLR(String path) {
        PLR plr = new PLR();
        File f = new File(path);
        if(!path.endsWith(".plr")) {
            log.error("not current PLR file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String str;
        String name;
        setProgress(MAX_PROGRESS);
        return plr;
    }

    public static MP parseMP(String path) {
        MP mp = null;
        if(!path.endsWith(".mp")) {
            log.error("not current MP file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String name = "";
        Block list[][] = new Block[DefaultConstant.MAX_MAP_SIZE][DefaultConstant.MAX_MAP_SIZE];
        mp = new MP(name, list);
        setProgress(MAX_PROGRESS);
        return mp;
    }

    /**
     * 设置状态
     * @return
     */
    public static double getProgress() {
        return progress;
    }

    private static void setProgress(double p) {
        if(p > MAX_PROGRESS) p = MAX_PROGRESS;
        progress = p;
    }

    private static SCE parseSCE(String str, SCE sce) {
        if(str.isEmpty()) return sce;
        StringBuilder stringBuilder = new StringBuilder();
        char list[] = str.toCharArray();
        boolean isKey = true;
        String key = null, value = null;
        if(list[0] == '.') {
            for(int i=1; i<list.length; i++) {
                if(list[i] == ' ' && isKey) {
                    isKey = false;
                    key = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append(list[i]);
            }
            value = stringBuilder.toString();
            if(key == null) {
                return sce;
            }
            switch(key) {
                case "name":
                    sce.setName(value);
                    break;
                case "version":
                    sce.setVersion(Double.parseDouble(value));
                    break;
                case "main":
                    sce.setMain(Boolean.getBoolean(value));
                    break;
            }
            return sce;
        }
        if(list[0] == '[') {
            for(int i=1; i<list.length; i++) {
                if(list[i] == ']' && isKey) {
                    isKey = false;
                    key = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append(list[i]);
            }
            value = stringBuilder.toString();
            sce.addWords(Integer.parseInt(key), value);
        }
        return sce;
    }

}
