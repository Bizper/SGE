package base;

import constant.DefaultConstant;
import constant.EventType;
import intf.Concept;
import intf.GameAction;
import intf.GameUnit;
import intf.action.RangeOperation;
import intf.action.SelfOperation;
import intf.action.TargetOperation;
import service.ConceptFactory;

public abstract class Action implements Concept, GameAction, DefaultConstant {

    protected TargetOperation<GameUnit> target_action;

    protected RangeOperation<GameUnit, Integer> range_action;

    protected SelfOperation<GameUnit> self_action;

    protected String name = DEFAULT_NAME;

    protected int ID = DEFAULT_ID;

    protected boolean isUsed = false;

    public Concept setName(String name) {
        this.name = name;
        onFlush(EventType.TAKE_NAME_CHANGE);
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
        onFlush(EventType.TAKE_ID_CHANGE);
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
    public void onFlush(EventType type) { }

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
        if(target_action == null) return;
        target_action.action(trigger, target);
        onFlush(EventType.TAKE_SPELL);
    }

    @Override
    public void cast(GameUnit trigger, int x, int y, int radius) {
        if(range_action == null) return;
        range_action.action(trigger, x, y, radius);
        onFlush(EventType.TAKE_SPELL);
    }

    public void cast(GameUnit trigger) {
        if(self_action == null) return;
        self_action.action(trigger);
        onFlush(EventType.TAKE_SPELL);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Action [");
        sb.append("target_action=").append(target_action);
        sb.append(", range_action=").append(range_action);
        sb.append(", self_action=").append(self_action);
        sb.append(", name='").append(name).append('\'');
        sb.append(", ID=").append(ID);
        sb.append(", isUsed=").append(isUsed);
        sb.append(']');
        return sb.toString();
    }

}
