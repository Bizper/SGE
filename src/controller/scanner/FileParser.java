package controller.scanner;

import constant.DefaultConstant;
import mapping.*;
import mapping.inside.Block;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

public class FileParser {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static double MIN_PROGRESS = 0.0;
    private static double MAX_PROGRESS = 100.0;

    private static double progress = MIN_PROGRESS;

    public static SCE parseSCE(String path) {
        return null;
    }

    public static PLR parsePLR(String path) {
        return null;
    }

    public static MP parseMP(String path) {
        MP mp;
        File f = new File(path);
        try {
            setProgress(MIN_PROGRESS);
            String name;
            Block list[][] = new Block[DefaultConstant.MAX_MAP_SIZE][DefaultConstant.MAX_MAP_SIZE];
            String str;
            br = new BufferedReader(new FileReader(f));
            while((str = br.readLine()) != null) {
                //TODO
            }
            setProgress(MAX_PROGRESS);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            mp = null;
        }
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
