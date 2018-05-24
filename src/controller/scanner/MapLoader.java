package controller.scanner;

import mapping.SCE;


/**
 * 负责加载SCE文件并实例化其中的数据。
 */
public class MapLoader {

    public static SCE load() {
        return FileScanner.searchForSCE();
    }

}
