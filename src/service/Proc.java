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

    public synchronized static Concept findConceptByID(int id) {
        return list.get(id);
    }

    public synchronized static Concept register(Concept concept) {
        if(empty.size() > 0) {
            list.put(empty.get(empty.size() - 1), concept);
            concept.setID(empty.get(empty.size() - 1));
            empty.remove(empty.size() - 1);
            return concept;
        }
        list.put(ID, concept);
        concept.setID(ID);
        ID ++;
        return concept;
    }

    public synchronized static Concept logout(Concept concept) {
        list.remove(concept.getID(), concept);
        empty.add(concept.getID());
        concept.setID(DEFAULT_ID);
        return concept;
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
