package controller.scanner;

import mapping.MP;
import mapping.PLR;
import mapping.SCE;
import util.Log;

/**
 * 根据SCE文件中的路径查找对应的PLR和MP文件
 */
public class FileScanner {

    private static Log log = Log.getInstance(FileScanner.class);

    private static String main = "main.sce";

    public static SCE searchForSCE(String path) {
        log.log("searching file for " + path + main + "...");
        return FileParser.parseSCE(path + main);
    }

    public static SCE searchForSCE(String path, String filename) {
        log.log("searching file for " + path + filename + "...");
        return FileParser.parseSCE(path + filename);
    }

    public static PLR searchForPLR(String path) {
        return FileParser.parsePLR(path);
    }

    public static MP searchForMP(String path) {
        return FileParser.parseMP(path);
    }

}
