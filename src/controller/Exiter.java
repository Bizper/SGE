package controller;

import resources.Strings;
import service.Proc;
import service.TaskManager;
import util.ConfigUtil;
import util.Log;

public class Exiter implements Strings {

    private static Log log = Log.getInstance(Exiter.class);

    public static void release() {
        log.log(releasing_resource);
        ConfigUtil.saveConstant();
        TaskManager.getInstance().closeAll();
        Proc.logoutAll();
        exit();
    }

    public static void exit() {
        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
        String className = elements[2].getClassName();
        if(className.equals(Exiter.class.getName())) {
            log.log("唤起退出的模块为: " + className);
            System.exit(0);
        } else {
            log.warning("从" + className +  "发起了一次风险性的退出调用.");
            System.exit(1);
        }
    }

}
