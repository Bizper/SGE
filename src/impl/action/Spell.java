package impl.action;

import base.Action;
import intf.GameUnit;

import java.awt.*;

public class Spell extends Action {

    @Override
    public void paint(Graphics g) {

    }

    public Spell() {}

    public Spell(String name) {
        setName(name);
    }

}
