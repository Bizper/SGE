package controller;

import service.Proc;
import service.TaskManager;
import util.ConfigUtil;
import util.Log;

public class Exiter {

    private static Log log = Log.getInstance(Exiter.class);

    public static void release() {
        log.log("releasing resource...");
        ConfigUtil.saveConstant();
        TaskManager.getInstance().closeAll();
        Proc.logoutAll();
        exit();
    }

    public static void exit() {
        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
        String className = elements[2].getClassName();
        if(className.equals(Exiter.class.getName())) {
            log.log("exit from " + className);
            System.exit(0);
        } else {
            log.warning("dangerous exit from " + className);
            System.exit(1);
        }
    }

}
