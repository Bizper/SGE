package impl;

import base.Instance;
import service.ConceptFactory;

public class Player extends Instance {

    private Player() {}

    public static Player getInstance() {
        return ConceptFactory.getInstance(Player.class);
    }

}
