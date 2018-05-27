package constant;

public enum BlockType {

    NORMAL(0, "普通地块"),
    INACCESSIBLE(1, "不可通行"),
    HURT(2, "受伤地块"),
    HEAL(3, "治疗地块"),
    TRIGGER(4, "触发事件地块");


    private int type;
    private String desc;

    BlockType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "BlockType [" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                ']';
    }
}
