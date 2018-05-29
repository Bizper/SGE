package impl.person;

import base.Instance;

import java.util.Arrays;

public class Monster extends Instance {

    @Override
    public String toString() {
        return "Monster [" +
                "hp=" + hp +
                ", maxHp=" + maxHp +
                ", mp=" + mp +
                ", maxMp=" + maxMp +
                ", attack=" + attack +
                ", defence=" + defence +
                ", default_type=" + default_type +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", moveSpeed=" + moveSpeed +
                ", owner=" + owner +
                ", pack_size=" + pack_size +
                ", x=" + x +
                ", y=" + y +
                ", spell_list=" + spell_list +
                ", pack=" + (pack == null ? null : Arrays.asList(pack)) +
                ", ID=" + ID +
                ", isUsed=" + isUsed +
                ']';
    }
}
