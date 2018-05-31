package mapping;

import intf.GameAction;
import intf.GameUnit;

import java.util.HashMap;
import java.util.Map;

/**
 * 单位数据的映射
 */
public class PLR {

    private Map<String, GameUnit> units = new HashMap<>();

    private Map<String, GameAction> actions = new HashMap<>();

    public PLR add(String id, GameAction action) {
        actions.put(id, action);
        return this;
    }

    public PLR add(String id, GameUnit unit) {
        units.put(id, unit);
        return this;
    }

}
