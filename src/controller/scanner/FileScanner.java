package controller.scanner;

import mapping.MP;
import mapping.PLR;
import mapping.SCE;
import org.jetbrains.annotations.NotNull;
import util.Log;

/**
 * 根据SCE文件中的路径查找对应的PLR和MP文件
 */
public class FileScanner {

    private static Log log = Log.getInstance(FileScanner.class);

    private static String main = "main";

    private static String SCE_SUFFIX = ".sce";

    private static String PLR_SUFFIX = ".plr";

    private static String MP_SUFFIX = ".mp";

    public static SCE searchForSCE(String path) {
        return searchForSCE(path, main);
    }

    public static SCE searchForSCE(String path, String filename) {
        log.log("searching file for " + path + filename + SCE_SUFFIX + "...");
        SCE sce = FileParser.parseSCE(path + filename + SCE_SUFFIX);
        PLR plr = searchForPLR(path + filename + PLR_SUFFIX);
        sce.setPlr(plr);
        return sce;
    }

    public static PLR searchForPLR(String path) {
        return FileParser.parsePLR(path);
    }

    public static MP searchForMP(String path) {
        return FileParser.parseMP(path);
    }

}
