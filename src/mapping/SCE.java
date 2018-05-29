package mapping;

/**
 * SCE文件的映射，SCE文件会被解析为一个SCE类并使用MapLoader加载进游戏主程序
 */
public class SCE {

    private String name;

    public boolean isEmpty() {
        return false;
    }

    public String getName() {
        return name;
    }

    public SCE setName(String name) {
        this.name = name;
        return this;
    }
}
