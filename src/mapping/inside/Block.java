package mapping.inside;

import types.BlockEventType;
import types.BlockType;
import util.RandomUtil;

public class Block {

    public BlockType prop;

    private BlockEventType blockEventType = BlockEventType.NORMAL;

    public BlockType getProp() {
        return prop;
    }

    public void setProp(BlockType prop) {
        this.prop = prop;
    }

    public Block() {
        this(RandomUtil.getBoolean() ? "RiverWood" : "Blueberry", RandomUtil.getBoolean() ? BlockType.TYPE_ROAD : BlockType.TYPE_GLASS);
    }

    public Block(String name, BlockType prop) {
        this.prop = prop;
    }

    public BlockEventType getBlockEventType() {
        return blockEventType;
    }

    public Block setBlockEventType(BlockEventType blockEventType) {
        this.blockEventType = blockEventType;
        return this;
    }

    @Override
    public String toString() {
        return "Block [" +
                "prop=" + prop +
                ", blockEventType=" + blockEventType +
                ']';
    }
}
