package service;

import intf.Concept;
import util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

public class ConceptFactory {

    private static int DEFAULT_SIZE = 10;//默认实例池大小

    private static int CURRENT_SIZE = DEFAULT_SIZE;

    private static List<Concept> pool = new ArrayList<>(DEFAULT_SIZE);//实例池

    private static int cursor = 0;//当前使用的实例数量

    private static Class<Concept> DEFAULT_CLASS;

    static {
        DEFAULT_CLASS = ConfigUtil.getDefaultModel();
        for(int i=0; i<CURRENT_SIZE; i++) {
            pool.add(getModel());
        }
    }

    private synchronized static Concept getModel() {
        try {
            return DEFAULT_CLASS.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized static Concept getInstance() {
        if(cursor >= CURRENT_SIZE) {//检查当前游标是否超限，超限的话直接返回新建的实例
            return getNewInstance();
        }
        Concept concept = pool.get(cursor);//获得当前游标的实例
        int local_cursor = 0;//创建本地游标
        while(concept.getUsed()) {//如果获取的实例已经被使用了
            concept = pool.get(local_cursor);//根据本地游标开始获取实例
            local_cursor ++;//本地游标增加
            if(local_cursor >= CURRENT_SIZE) {//如果遍历完成后发现全都被使用了，直接返回新建的实例
                return getNewInstance();
            }
        }
        concept.create();//标记为已经使用
        cursor ++;//游标加1
        Proc.register(concept);
        return concept;
    }

    private synchronized static Concept getNewInstance() {
        Concept concept = getModel();
        concept.create();
        Proc.register(concept);
        return concept;
    }

    public synchronized static void receive(Concept concept) {
        if(pool.contains(concept)) {
            cursor --;
        } else {
            pool.add(concept);
            CURRENT_SIZE = pool.size();
        }
        Proc.logout(concept);
    }

    public static String asString() {
        return "ConceptFactory [" + "size=" + CURRENT_SIZE + " cursor=" + cursor + "]";
    }

    public static void printAll() {
        for(Concept concept : pool) {
            System.out.println("ConceptFactory [" + concept + " id=" + concept.getID() + "]");
        }
    }

}
