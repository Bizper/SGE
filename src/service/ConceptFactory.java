package service;

import intf.Concept;
import util.ConfigUtil;
import util.Log;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ConceptFactory {

    private static Class<? extends Concept> DEFAULT_CLASS;

    private static Log log = Log.getInstance(ConceptFactory.class);

    static {
        DEFAULT_CLASS = getDefaultModel();
    }

    /**
     * 获取默认的model
     * @return
     */
    private static Object getModel() {
        try {
            Constructor cons =  DEFAULT_CLASS.getConstructor();
            cons.setAccessible(true);
            return cons.newInstance();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public static <T extends Concept> int getInstanceID(Class<T> cls) {
        log.log("接受到模具为: " + cls);
        DEFAULT_CLASS = cls;
        return getNewInstance().getID();
    }

    public static <T extends Concept> T getInstance(Class<T> cls) {
        DEFAULT_CLASS = cls;
        return (T) getNewInstance();
    }

    private static Concept getNewInstance() {
        Concept concept = (Concept) getModel();
        if(concept == null) {
            return null;
        }
        concept.create();
        log.log("创建实例: " + concept.getClass().getName());
        Proc.register(concept);
        return concept;
    }

    public static void receive(Concept concept) {
        Proc.logout(concept);
    }

    private static Class getDefaultModel() {
        String classUrl = ConfigUtil.getValue("default.model");
        Class<?> cls = null;//default model
        try {
            log.log("正在搜索实例类: " + classUrl);
            cls = ClassLoader.getSystemClassLoader().loadClass(classUrl);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        return cls;
    }

    public static void print() {
        log.log("当前模型为: " + DEFAULT_CLASS);
    }

}
