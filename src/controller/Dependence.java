package controller;

import controller.scanner.MapLoader;
import mapping.SCE;
import screen.Display;
import util.Log;

import java.awt.event.KeyEvent;

public class Dependence {

    private static Log log = Log.getInstance(Dependence.class);

    private static Dependence dependence;

    private static Display display;

    private int task = 0;

    private Dependence() {
        init();
    }

    static Dependence launch() {
        if(dependence == null) dependence = new Dependence();
        return dependence;
    }

    private void init() {
        log.log("initiate " + Dependence.class.getName() + " for logic progress.");
        load();
        display = Display.getInstance();
        task = TaskManager.getInstance().addTask((e) -> deal(), 1000);
        log.log(Dependence.class.getName() + " initiation complete.");
    }

    private void deal() {
        display.append("get message from message queue.");
    }

    private void load() {
        SCE sce = MapLoader.load("./");
        if(sce == null) {
            log.error("empty SCE file.");
            return;
        }

    }

    /**
     * 0x4a 结束当前任务
     * 0x4b 结束所有任务
     * 0x4c 清空消息栏
     * 0x4d 清空当前缓冲区
     * 0x9a 强制退出程序
     */
    public static void interrupt(int code) {
        switch(code) {
            case 0x4a:
                TaskManager.getInstance().close(dependence.task);
                break;
            case 0x9a:
                Exiter.exit(Dependence.class.getName());
                break;
        }
    }

}
