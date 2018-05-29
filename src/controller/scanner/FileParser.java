package controller.scanner;

import constant.DefaultConstant;
import mapping.*;
import mapping.inside.Block;
import util.Log;

import java.io.*;

public class FileParser {

    private static Log log = Log.getInstance(FileParser.class);

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static double MIN_PROGRESS = 0.0;
    private static double MAX_PROGRESS = 100.0;

    private static double progress = MIN_PROGRESS;

    private static BufferedReader init(File f) {
        try {
            return new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            log.error("file not found. check your file location.");
        }
        return null;
    }

    public static SCE parseSCE(String path) {
        SCE sce = null;
        File f = new File(path);
        if(!path.endsWith(".sce")) {
            log.error("not current SCE file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String str;
        String name;
        br = init(f);
        if(br == null) return null;
        try {
            sce = new SCE();
            while((str = br.readLine()) != null) {
            }
        } catch(IOException ioe) {
            log.error(ioe);
        }
        setProgress(MAX_PROGRESS);
        return sce;
    }

    public static PLR parsePLR(String path) {
        PLR plr = null;
        File f = new File(path);
        if(!path.endsWith(".plr")) {
            log.error("not current PLR file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String str;
        String name;
        br = init(f);
        if(br == null) return null;
        try {
            while((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch(IOException ioe) {
            log.error(ioe);
        }
        setProgress(MAX_PROGRESS);
        return plr;
    }

    public static MP parseMP(String path) {
        MP mp = null;
        File f = new File(path);
        if(!path.endsWith(".mp")) {
            log.error("not current MP file. check your file location.");
            return null;
        }
        setProgress(MIN_PROGRESS);
        String name = "";
        Block list[][] = new Block[DefaultConstant.MAX_MAP_SIZE][DefaultConstant.MAX_MAP_SIZE];
        String str;
        br = init(f);
        if(br == null) return null;
        try {
            while((str = br.readLine()) != null) {
                System.out.println(str);
            }
            mp = new MP(name, list);
        } catch(IOException ioe) {
            log.error(ioe);
        }
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

}
