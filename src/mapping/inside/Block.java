package mapping.inside;

import constant.BlockType;

public class Block {

    public String name;

    public BlockType prop;

    public String getName() {
        return name;
    }

    public BlockType getProp() {
        return prop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProp(BlockType prop) {
        this.prop = prop;
    }

    public Block(String name, BlockType prop) {
        this.name = name;
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "Block [" +
                "name='" + name + '\'' +
                ", prop=" + prop +
                ']';
    }
}
