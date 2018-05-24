package intf;

import intf.action.RangeOperation;
import intf.action.SelfOperation;
import intf.action.TargetOperation;

public interface GameAction {

    GameAction setAction(TargetOperation<GameUnit> to);

    GameAction setAction(RangeOperation<GameUnit, Integer> ro);

    GameAction setAction(SelfOperation<GameUnit> so);

    void cast(GameUnit trigger, GameUnit target);

    void cast(GameUnit trigger, int x, int y, int radius);

    void cast(GameUnit trigger);

}
