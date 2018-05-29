package impl;

import base.Instance;
import service.ConceptFactory;
import service.Proc;

public class Player extends Instance {

    private Player() {}

    public static Player getInstance() {
        return (Player) Proc.get(ConceptFactory.getInstance(Player.class));
    }

}
