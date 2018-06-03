package controller;

import controller.task.TaskManager;
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
        if(cls == Exiter.class) {
            log.log("exit from " + cls.getName());
            System.exit(0);
        } else {
            log.warning("exit from " + cls.getName());
            System.exit(1);
        }
    }

}
