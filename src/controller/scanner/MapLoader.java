package controller.scanner;

import controller.scanner.error.TypeErrorException;
import mapping.SCE;


/**
 * 负责加载SCE文件并实例化其中的数据。
 */
public class MapLoader {

    public static SCE load(String path) {
        return FileScanner.searchForSCE(path);
    }

}
