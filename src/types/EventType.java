package types;

public enum EventType {

    PACK_FULL(0, "背包已满"),
    PACK_EMPTY(1, "背包为空"),
    PACK_ADD_SUCCESS(2, "添加至背包"),
    PACK_REMOVE_SUCCESS(3, "拿出背包"),
    GET_HEAL(4, "受到治疗"),
    GET_HURT(5, "受到伤害"),
    TAKE_ATTACK(6, "发动攻击"),
    TAKE_SPELL(7, "使用技能"),
    TAKE_MOVE(8, "移动"),
    TAKE_MOVE_SPEED_CHANGE(9, "移动速度改变"),
    TAKE_LEVEL_CHANGE(10, "等级改变"),
    TAKE_ID_CHANGE(11, "ID改变"),
    TAKE_STATUS_CHANGE(12, "状态改变"),
    TAKE_NAME_CHANGE(13, "名称改变"),
    TAKE_MP_CHANGE(14, "魔法改变"),
    TAKE_HP_CHANGE(15, "生命改变"),
    TAKE_MAX_MP_CHANGE(16, "最大魔法改变"),
    TAKE_MAX_HP_CHANGE(17, "最大生命改变"),
    TAKE_LOCATION_CHANGE(18, "位置改变"),
    TAKE_DEFENCE_CHANGE(19, "防御力改变"),
    TAKE_ATTACK_CHANGE(20, "攻击力改变");

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