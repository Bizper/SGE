package controller;

import util.ConfigUtil;
import util.Log;

public class Exiter {

    private static Log log = Log.getInstance(Exiter.class);

    public static void release() {
        log.log("releasing resource...");
        ConfigUtil.saveConstant();
        TaskManager.getInstance().closeAll();
        exit(Exiter.class.getName());
    }

    public static void exit(String str) {
        log.log("exit from " + str);
        System.exit(0);
    }

}
