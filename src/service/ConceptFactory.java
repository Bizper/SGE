package service;

import intf.Concept;
import util.ConfigUtil;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConceptFactory {

    private static int DEFAULT_SIZE = 10;//默认实例池大小

    private static int CURRENT_SIZE = 0;

    private static List<Concept> pool = new ArrayList<>(DEFAULT_SIZE);//实例池

    private static int cursor = 0;//当前使用的实例数量

    private static Class<?> DEFAULT_CLASS;

    private static Log log = Log.getInstance(ConceptFactory.class);

    static {
        DEFAULT_CLASS = getDefaultModel();
    }

    /**
     * 获取默认的model
     * @return
     */
    private synchronized static Object getModel() {
        try {
            return DEFAULT_CLASS.getConstructor().newInstance();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public synchronized static <T> int getInstanceID(Class<T> cls) {
        log.log("receive model: " + cls);
        DEFAULT_CLASS = cls;
        if(cursor >= CURRENT_SIZE) {//检查当前游标是否超限，超限的话直接返回新建的实例
            return getNewInstance().getID();
        }
        Concept concept = pool.get(cursor);//获得当前游标的实例
        int local_cursor = 0;//创建本地游标
        while(concept.getUsed()) {//如果获取的实例已经被使用了
            concept = pool.get(local_cursor);//根据本地游标开始获取实例
            local_cursor ++;//本地游标增加
            if(local_cursor >= CURRENT_SIZE) {//如果遍历完成后发现全都被使用了，直接返回新建的实例
                return getNewInstance().getID();
            }
        }
        concept.create();//标记为已经使用
        cursor ++;//游标加1
        return Proc.register(concept);
    }

    public synchronized static <T> T getInstance(Class<T> cls) {
        log.log("receive model: " + cls);
        DEFAULT_CLASS = cls;
        if(cursor >= CURRENT_SIZE) {//检查当前游标是否超限，超限的话直接返回新建的实例
            return (T) getNewInstance();
        }
        Concept concept = pool.get(cursor);//获得当前游标的实例
        int local_cursor = 0;//创建本地游标
        while(concept.getUsed()) {//如果获取的实例已经被使用了
            concept = pool.get(local_cursor);//根据本地游标开始获取实例
            local_cursor ++;//本地游标增加
            if(local_cursor >= CURRENT_SIZE) {//如果遍历完成后发现全都被使用了，直接返回新建的实例
                return (T) getNewInstance();
            }
        }
        concept.create();//标记为已经使用
        cursor ++;//游标加1
        Proc.register(concept);
        return (T) concept;
    }

    private synchronized static Concept getNewInstance() {
        Concept concept = (Concept) getModel();
        if(concept == null) {
            return null;
        }
        concept.create();
        log.log("creating model: " + concept);
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

    private synchronized static Class getDefaultModel() {
        String classUrl = ConfigUtil.getValue("default.model");
        Class<?> cls = null;//default model
        try {
            log.log("scanning folders for " + classUrl);
            cls = ClassLoader.getSystemClassLoader().loadClass(classUrl);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        return cls;
    }

    public static void print() {
        log.log("size=" + CURRENT_SIZE + ", cursor=" + cursor);
    }

    public static void printAll() {
        for(Concept concept : pool) {
            log.log(concept);
        }
    }

}
