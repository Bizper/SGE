package impl;

import base.Action;
import intf.GameUnit;

public class Spell extends Action {

    @Override
    public void cast(GameUnit trigger, GameUnit target) {
        super.cast(trigger, target);
        //TODO
    }

    @Override
    public void cast(GameUnit trigger, int x, int y, int radius) {
        //TODO
    }
}
