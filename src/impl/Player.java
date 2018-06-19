package impl;

import base.Instance;
import constant.StatusType;
import service.ConceptFactory;

import java.awt.*;

public class Player extends Instance {

    public Player() {
        setStatus(StatusType.STATUS_NON_SHOW);
    }

    @Override
    public void paint(Graphics g) { }

    public static Player getInstance() {
        return ConceptFactory.getInstance(Player.class);
    }

    public void onCreate() {
        setName("John Wick");
    }

}
