package base;

import constant.DefaultConstant;
import intf.Concept;
import intf.GameAction;
import intf.GameUnit;
import intf.action.RangeOperation;
import intf.action.SelfOperation;
import intf.action.TargetOperation;
import service.ConceptFactory;

public abstract class Action implements Concept, GameAction, DefaultConstant {

    private TargetOperation<GameUnit> target_action;

    private RangeOperation<GameUnit, Integer> range_action;

    private SelfOperation<GameUnit> self_action;

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
    public GameAction setAction(SelfOperation<GameUnit> so) {
        this.self_action = so;
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

    public void cast(GameUnit trigger) {
        self_action.action(trigger);
        onFlush();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Action{");
        sb.append("target_action=").append(target_action);
        sb.append(", range_action=").append(range_action);
        sb.append(", self_action=").append(self_action);
        sb.append(", name='").append(name).append('\'');
        sb.append(", ID=").append(ID);
        sb.append(", isUsed=").append(isUsed);
        sb.append('}');
        return sb.toString();
    }

}
