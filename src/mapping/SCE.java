package mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * SCE文件的映射，SCE文件会被解析为一个SCE类并使用MapLoader加载进游戏主程序
 */
public class SCE {

    private PLR plr;

    private String name;

    private double version;

    private boolean main;

    private int start;

    private boolean empty = true;

    private Map<String, String> maps = new HashMap<>();

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

    public int getStart() {
        return start;
    }

    public SCE setStart(int start) {
        this.start = start;
        return this;
    }

    public String getWords(int id) {
        return sentences.get(id);
    }

    public Map<Integer, String> getSentences() {
        return sentences;
    }

    public SCE addMap(String id, String mp) {
        maps.put(id, mp);
        return this;
    }

    public String getMap(String id) {
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

    public PLR getPlr() {
        return plr;
    }

    public SCE setPlr(PLR plr) {
        this.plr = plr;
        return this;
    }

    @Override
    public String toString() {
        return "SCE [" +
                "plr=" + plr +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", main=" + main +
                ", start=" + start +
                ", empty=" + empty +
                ", maps=" + maps +
                ", sentences=" + sentences +
                ']';
    }
}
