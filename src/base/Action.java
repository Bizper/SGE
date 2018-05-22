package base;

import constant.DefaultConstant;
import intf.Concept;
import intf.GameAction;
import intf.GameUnit;
import intf.action.RangeOperation;
import intf.action.TargetOperation;
import service.ConceptFactory;

public abstract class Action implements Concept, GameAction, DefaultConstant {

    private TargetOperation<GameUnit> target_action;

    private RangeOperation<GameUnit, Integer> range_action;

    private String name = DEFAULT_NAME;

    private int ID = DEFAULT_ID;

    private boolean isUsed = false;

    public Concept setName(String name) {
        this.name = name;
        onFlush();
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Concept setID(int id) {
        ID = id;
        onFlush();
        return this;
    }

    @Override
    public boolean getUsed() {
        return isUsed;
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
        return null;
    }

    @Override
    public void onDestroy() { }

    @Override
    public void onCreate() { }

    @Override
    public void onFlush() { }

    @Override
    public void onPaint() { }

    @Override
    public GameAction setAction(TargetOperation<GameUnit> to) {
        this.target_action = to;
        return this;
    }

    @Override
    public GameAction setAction(RangeOperation<GameUnit, Integer> ro) {
        this.range_action = ro;
        return this;
    }

    @Override
    public void cast(GameUnit trigger, GameUnit target) {
        target_action.action(trigger, target);
        onFlush();
    }

    @Override
    public void cast(GameUnit trigger, int x, int y, int radius) {
        range_action.action(trigger, x, y, radius);
        onFlush();
    }
}
