package constant;

public enum EventType {

    PACK_FULL(0, "背包已满"),
    PACK_EMPTY(1, "背包为空"),
    PACK_ADD_SUCCESS(2, "添加至背包"),
    PACK_REMOVE_SUCCESS(3, "拿出背包");

    private int type;
    private String desc;

    EventType(int type, String desc) {
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
        return "EventType [" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                ']';
    }
}
