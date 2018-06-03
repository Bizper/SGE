package controller;

import controller.model.RunModel;
import controller.scanner.FileScanner;
import controller.scanner.MapLoader;
import controller.task.TaskManager;
import impl.Player;
import mapping.SCE;
import screen.BufferedScreen;
import screen.Display;
import service.Proc;
import util.Log;

public class Dependence {

    private static Log log = Log.getInstance(Dependence.class);

    private static Dependence dependence;

    private static Display display;

    private int task = 0;

    private RunModel runModel;

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
        task = TaskManager.getInstance().addTask((e) -> flush(), 1000/60, "DEFAULT");
        log.log(Dependence.class.getName() + " initiation complete.");
    }

    private void flush() {
        display.append(runModel);
    }

    private void load() {
        log.log("start initial world...");

        runModel = new RunModel();
        SCE sce = MapLoader.load("./");
        if(sce == null) {
            log.error("empty SCE file.");
            return;
        }
        runModel.setSentences(sce.getSentences());
        runModel.setMP(FileScanner.searchForMP("./", sce.getMap("main")));
        runModel.setCurrentPlayer(Player.getInstance());
        runModel.setStep(sce.getStart());

        log.log("initial game world with \"" + sce.getName() + "\" settings...");
    }

    private void doNext() {
        runModel.doNext();
        BufferedScreen.write(runModel.getWords());
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
     * 0x4a 进行游戏的下一步
     * 0x4b 进行当前动作
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
            case 0x4a:
                dependence.doNext();
                break;
            case 0x4b:
                break;
            case 0x9a:
                Exiter.exit(Dependence.class);
                break;
        }
    }

}
