package mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * SCE文件的映射，SCE文件会被解析为一个SCE类并使用MapLoader加载进游戏主程序
 */
public class SCE {

    private String name;

    private double version;

    private boolean main;

    private boolean empty = true;

    private Map<String, MP> maps = new HashMap<>();

    private Map<Integer, String> sentences = new HashMap<>();

    public boolean isMain() {
        return main;
    }

    public SCE setMain(boolean main) {
        this.main = main;
        return this;
    }

    public double getVersion() {
        return version;
    }

    public SCE setVersion(double version) {
        this.version = version;
        return this;
    }

    public SCE addWords(int id, String words) {
        sentences.put(id, words);
        return this;
    }

    public String getWords(int id) {
        return sentences.get(id);
    }

    public SCE addMap(String id, MP mp) {
        maps.put(id, mp);
        return this;
    }

    public MP getMap(String id) {
        return maps.get(id);
    }

    public boolean isEmpty() {
        return empty;
    }

    public String getName() {
        return name;
    }

    public SCE setName(String name) {
        this.name = name;
        return this;
    }
}
