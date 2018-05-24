package impl.action;

import base.Action;
import intf.GameUnit;

public class Spell extends Action {

    public Spell() {}

    public Spell(String name) {
        setName(name);
    }

    @Override
    public void cast(GameUnit trigger, GameUnit target) {
        super.cast(trigger, target);
        //TODO
    }

    @Override
    public void cast(GameUnit trigger, int x, int y, int radius) {
        //TODO
    }

    public void cast(GameUnit trigger) {
        //TODO
    }
}
