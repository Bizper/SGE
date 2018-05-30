package controller;

import controller.scanner.MapLoader;
import mapping.SCE;
import screen.BufferedScreen;
import screen.Display;
import util.Log;

import java.awt.event.KeyEvent;

public class Dependence {

    private static Log log = Log.getInstance(Dependence.class);

    private static Dependence dependence;

    private static Display display;

    private int task = 0;

    private int step = 1;

    private SCE sce;

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
        task = TaskManager.getInstance().addTask((e) -> deal(), 5000, "DEFAULT");
        log.log(Dependence.class.getName() + " initiation complete.");
    }

    private void deal() {
        display.append(sce.getWords(step));
        step ++;
    }

    private void load() {
        sce = MapLoader.load("./");
        if(sce == null) {
            log.error("empty SCE file.");
            return;
        }
        log.log("initial game world with \"" + sce.getName() + "\" settings...");
    }

    /**
     * 0x4a 结束当前任务
     * 0x4b 结束所有任务
     * 0x4c 清空消息栏
     * 0x4d 清空当前缓冲区
     * 0x4e 输出当前运行的所有任务
     * 0x9a 强制退出程序
     */
    public static void interrupt(int code) {
        switch(code) {
            case 0x4a:
                TaskManager.getInstance().close(dependence.task);
                break;
            case 0x4b:
                TaskManager.getInstance().closeAll();
                break;
            case 0x4c:
                display.clear();
                break;
            case 0x4d:
                BufferedScreen.clear();
                break;
            case 0x4e:
                TaskManager.getInstance().printAll();
                break;
            case 0x9a:
                Exiter.exit(Dependence.class.getName());
                break;
        }
    }

}
