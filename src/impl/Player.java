package impl;

import base.Instance;
import types.StatusType;
import service.ConceptFactory;

import java.awt.*;

public final class Player extends Instance {

    private static int num = 0;

    public Player() {
        setStatus(StatusType.STATUS_NON_SHOW);
    }

    @Override
    public void paint(Graphics g) { }

    public static Player getInstance() {
        return ConceptFactory.getInstance(Player.class);
    }

    public void onCreate() {
        setName("Player " + num);
        num++;
    }

}
