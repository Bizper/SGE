package base;

import constant.DefaultConstant;
import intf.Concept;
import intf.GameUnit;
import service.ConceptFactory;

public abstract class Spell implements Concept, DefaultConstant {

    private int ID = DEFAULT_ID;

    private boolean isUsed = false;

    public abstract void cast(GameUnit trigger, GameUnit target);

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
    public boolean getUsed() {
        return isUsed;
    }

    @Override
    public Concept create() {
        isUsed = true;
        onCreate();
        return this;
    }

    @Override
    public Concept destroy() {
        isUsed = false;
        onDestroy();
        ConceptFactory.receive(this);
        return this;
    }

    @Override
    public void onDestroy() { }

    @Override
    public void onCreate() { }

    @Override
    public void onFlush() { }

    @Override
    public void onPaint() { }
}
