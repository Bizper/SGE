package controller;

import util.ConfigUtil;
import util.Log;

public class Exiter {

    private static Log log = Log.getInstance(Exiter.class);

    public static void release() {
        log.log("releasing resource...");
        ConfigUtil.saveConstant();
        TaskManager.getInstance().closeAll();
        exit(Exiter.class);
    }

    public static void exit(Class<?> cls) {
        log.log("exit from " + cls.getName());
        if(cls == Exiter.class) System.exit(0);
        else System.exit(1);
    }

}
