package constant;

public enum StatusType {

    STATUS_DEFAULT,

    STATUS_NON_DRAWABLE,//无法被绘制
    STATUS_NON_CONTROL,//无法被控制

    STATUS_NON_SHOW;//无法被控制也无法被绘制，上面两个属性的综合


    public static StatusType getStatus(String name) {
        for(StatusType type : values()) {
            if(type.name().equals(name)) return type;
        }
        return null;
    }

}
