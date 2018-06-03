package mapping;

import constant.DefaultConstant;
import mapping.inside.Block;

import java.util.Arrays;

/**
 * 地图文件的映射
 */
public class MP implements DefaultConstant {

    private String name;

    private Block list[][] = new Block[MAX_MAP_SIZE][MAX_MAP_SIZE];

    public MP(String name, Block[][] list) {
        this.name = name;
        for(int i=0; i<MAX_MAP_SIZE; i++) {
            for(int j=0; j<MAX_MAP_SIZE; j++) {
                this.list[i][j] = list[i][j];
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Block get(int x, int y) {
        return list[x][y];
    }

    @Override
    public String toString() {
        return "MP [" +
                "name='" + name + '\'' +
                ", list=" + (list == null ? null : Arrays.asList(list)) +
                ']';
    }
}
