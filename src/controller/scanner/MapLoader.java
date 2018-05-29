package controller.scanner;

import mapping.SCE;
import util.Log;


/**
 * 负责加载SCE文件并实例化其中的数据。
 */
public class MapLoader {

    private static Log log = Log.getInstance(MapLoader.class);

    public static SCE load(String path) {
        log.log("load SCE file from " + path);
        return FileScanner.searchForSCE(path);
    }

}
