package mapping.inside;

import service.AssetManager;
import types.BlockEventType;
import types.BlockType;

public class Block {

    private BlockType prop;

    private String imageName;

    private BlockEventType blockEventType = BlockEventType.NORMAL;

    public BlockType getProp() {
        return prop;
    }

    public void setProp(BlockType prop) {
        this.prop = prop;
    }

    public Block() {
        BlockType type = BlockType.TYPE_ROAD;
        this.prop = type;
        this.imageName = AssetManager.getImageName(type);
    }

    public BlockEventType getBlockEventType() {
        return blockEventType;
    }

    public String getImageName() {
        return imageName;
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
