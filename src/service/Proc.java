package service;

import constant.DefaultConstant;
import intf.Concept;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proc implements DefaultConstant {

    private static Log log = Log.getInstance(Proc.class);

    private static int ID = DEFAULT_ID + 1;

    private static List<Integer> empty = new ArrayList<>();

    private static Map<Integer, Concept> list = new HashMap<>();

    public synchronized static Concept get(int id) {
        return list.get(id);
    }

    synchronized static int register(Concept concept) {
        int localID = ID;
        if(empty.size() > 0) {
            localID = empty.get(empty.size() - 1);
            list.put(localID, concept);
            concept.setID(localID);
            empty.remove(empty.size() - 1);
            return localID;
        }
        list.put(localID, concept);
        concept.setID(localID);
        IDPlus();
        return localID;
    }

    private synchronized static void IDPlus() {
        ID ++;
    }

    synchronized static void logout(Concept concept) {
        list.remove(concept.getID(), concept);
        empty.add(concept.getID());
        concept.setID(DEFAULT_ID);
        /**
         * in case of
         */
        System.gc();
    }

    public static void printAll() {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<Integer, Concept> e : list.entrySet()) {
            str.append("Proc [");
            str.append(e.getValue());
            str.append("]");
            log.log(str.toString());
            str.delete(0, str.length());
        }

    }

}
