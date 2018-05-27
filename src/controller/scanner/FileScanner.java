package controller.scanner;

import mapping.MP;
import mapping.PLR;
import mapping.SCE;

/**
 * 根据SCE文件中的路径查找对应的PLR和MP文件
 */
public class FileScanner {

    private static String filePath = "";

    static SCE searchForSCE(String path) {
        filePath = path;
        return FileParser.parseSCE(path + "main.sce");
    }

    public static PLR searchForPLR() {
        return null;
    }

    private static MP includeMP(String path) {
        return FileParser.parseMP(path);
    }

}
