package impl.person;

import base.Instance;

import java.util.Arrays;

public class Npc extends Instance {

    @Override
    public String toString() {
        return "Npc [" +
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
                ", x=" + x +
                ", y=" + y +
                ", spell_list=" + (spell_list == null ? null : Arrays.asList(spell_list)) +
                ", ID=" + ID +
                ", isUsed=" + isUsed +
                ']';
    }

}
