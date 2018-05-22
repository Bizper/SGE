package intf;

import intf.action.RangeOperation;
import intf.action.TargetOperation;

public interface GameAction {

    GameAction setAction(TargetOperation<GameUnit> to);

    GameAction setAction(RangeOperation<GameUnit, Integer> ro);

    void cast(GameUnit trigger, GameUnit target);

    void cast(GameUnit trigger, int x, int y, int radius);

}
