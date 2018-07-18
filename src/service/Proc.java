package service;

import intf.constant.DefaultConstant;
import intf.Concept;
import util.Log;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Proc implements DefaultConstant {

    private static Log log = Log.getInstance(Proc.class);

    private static int ID = DEFAULT_ID + 1;

    private static List<Integer> empty = new ArrayList<>();

    private static Map<Integer, Concept> list = new HashMap<>();

    private static Map<String, Integer> mapping = new HashMap<>();

    public synchronized static Concept get(int id) {
        return list.get(id);
    }

    public synchronized static Set<Integer> idSet() {
        return list.keySet();
    }

    public synchronized static Set<Concept> conceptFilterSet(Function<Concept, Boolean> func) {
        return list.values().stream().filter(func::apply).collect(Collectors.toSet());
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

    public synchronized static void logoutAll() {
        log.log("releasing all registered instances...");
        List<Concept> rest = new ArrayList<>(list.values());
        for(Concept concept : rest) {
            concept.destroy();
        }
        if(list.size() != 0) list.clear();
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

    public synchronized static void addMappingId(String id, int ID) {
        mapping.put(id, ID);
    }

    public synchronized static int getMappingId(String id) {
        return mapping.get(id);
    }

    public static void printAll() {
        if(list.isEmpty()) {
            log.log("no concept registered.");
            return;
        }
        log.log("");
        log.format("REGISTERED CONCEPT LIST:");
        log.format("%-5s%-20s%-10s\n", "ID", "NAME", "TYPE");
        for(Map.Entry<Integer, Concept> e : list.entrySet()) {
            Concept concept = e.getValue();
            log.format("%-5d%-20s%-10s\n", concept.getID(), concept.getName(), concept.getClass().getName());
        }
        System.out.println("\nID MAPPING LIST:");
        log.format("%-30s%-10s\n", "NAME", "ID");
        for(Map.Entry<String, Integer> e : mapping.entrySet()) {
            log.format("%-30s%-10s\n", "\"" + e.getKey() + "\" ", e.getValue());
        }
    }

}