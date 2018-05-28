package controller.scanner;

import controller.scanner.error.TypeErrorException;
import mapping.MP;
import mapping.PLR;
import mapping.SCE;

/**
 * 根据SCE文件中的路径查找对应的PLR和MP文件
 */
public class FileScanner {

    private static String filePath = "";

    public static SCE searchForSCE(String path) {
        filePath = path;
        return FileParser.parseSCE(path + "main.sce");
    }

    public static PLR searchForPLR(String path) {
        return FileParser.parsePLR(path);
    }

    public static MP searchForMP(String path) {
        return FileParser.parseMP(path);
    }

}
