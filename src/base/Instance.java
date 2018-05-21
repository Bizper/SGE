package base;

import constant.DefaultConstant;
import intf.Concept;
import intf.GameUnit;
import service.ConceptFactory;

public abstract class Instance implements Concept, GameUnit, DefaultConstant {

    private int hp = DEFAULT_HP;
    private int mp = DEFAULT_MP;

    private int ID = DEFAULT_ID;

    private boolean isUsed = false;//判断当前实例是否被使用

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public GameUnit setHp(int hp) {
        this.hp = hp;
        return this;
    }

    @Override
    public GameUnit setMp(int mp) {
        this.mp = mp;
        return this;
    }

    @Override
    public boolean getUsed() {
        return isUsed;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Concept setID(int id) {
        ID = id;
        return this;
    }

    @Override
    public Concept create() {
        this.isUsed = true;
        onCreate();
        return this;
    }

    @Override
    public Concept destroy() {
        this.isUsed = false;
        onDestroy();
        ConceptFactory.receive(this);
        return this;
    }

}