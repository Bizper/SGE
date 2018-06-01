package impl;

import base.Instance;
import service.ConceptFactory;

public class Player extends Instance {

    public static Player getInstance() {
        return ConceptFactory.getInstance(Player.class);
    }

    public void onCreate() {
        setName("John Wick");
    }

}
