package impl;

import intf.Concept;

public class Item implements Concept {

    @Override
    public Concept setName(String name) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public Concept setID(int id) {
        return null;
    }

    @Override
    public boolean getUsed() {
        return false;
    }

    @Override
    public Concept create() {
        return null;
    }

    @Override
    public Concept destroy() {
        return null;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onFlush() {

    }

    @Override
    public void onPaint() {

    }
}
