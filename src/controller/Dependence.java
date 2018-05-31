package controller;

import controller.scanner.MapLoader;
import mapping.SCE;
import screen.BufferedScreen;
import screen.Display;
import service.Proc;
import util.Log;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;

public class Dependence {

    private static Log log = Log.getInstance(Dependence.class);

    private static Dependence dependence;

    private static Display display;

    private int task = 0;

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
        task = TaskManager.getInstance().addTask((e) -> deal(), 1000/60, "DEFAULT");
        log.log(Dependence.class.getName() + " initiation complete.");
    }

    private void deal() {
        display.append();
    }

    private void load() {
        sce = MapLoader.load("./");
        if(sce == null) {
            log.error("empty SCE file.");
            return;
        }
        log.log("initial game world with \"" + sce.getName() + "\" settings...");
    }

    public static void interrupt(int code) {
        interrupt(code, null);
    }

    /**
     * 0x1a 结束当前任务
     * 0x1b 结束所有任务
     * 0x1c 输出当前运行的所有任务
     * 0x1d 输出所有注册单元
     * 0x2a 将字符串写入缓冲区
     * 0x3a 清空消息栏
     * 0x3b 清空当前缓冲区
     * 0x9a 强制退出程序
     */
    public static void interrupt(int code, String buffer) {
        log.log("interrupt code: " + code + " with buffer: " + (buffer == null ? "null" : buffer));
        switch(code) {
            case 0x1a:
                TaskManager.getInstance().close(dependence.task);
                break;
            case 0x1b:
                TaskManager.getInstance().closeAll();
                break;
            case 0x1c:
                TaskManager.getInstance().printAll();
                break;
            case 0x1d:
                Proc.printAll();
                break;
            case 0x2a:
                BufferedScreen.write(buffer);
                break;
            case 0x3a:
                display.clear();
                break;
            case 0x3b:
                BufferedScreen.clear();
                break;
            case 0x9a:
                Exiter.exit(Dependence.class);
                break;
        }
    }

}
