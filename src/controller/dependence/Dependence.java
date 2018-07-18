package controller.dependence;

import controller.Exiter;
import controller.model.RunModel;
import controller.scanner.FileScanner;
import controller.scanner.MapLoader;
import intf.constant.DefaultConstant;
import intf.constant.Interrupt;
import screen.Win;
import service.AssetManager;
import service.AudioManager;
import service.TaskManager;
import impl.Player;
import mapping.SCE;
import screen.BufferedScreen;
import service.Proc;
import util.ConfigUtil;
import util.Log;

import java.awt.event.KeyEvent;

import static intf.constant.Interrupt.*;

/**
 * 整个游戏系统的核心，称为依赖(Dependence)。
 * 通过使用中断(interrupt)方法来控制整个程序的运行。
 */
public class Dependence {

    private static Log log = Log.getInstance(Dependence.class);

    private static Dependence dependence;

    private RunModel runModel;

    private int task = 0;//记录核心任务的id

    private Dependence() {
        init();
    }

    public static void launch() {
        if(dependence == null) dependence = new Dependence();
    }

    private void init() {
        log.log("initiate System core for logic progress.");
        try {
            AssetManager.init();//调用资源管理器的初始化方法
            this.load();
            AudioManager.load("bgm", true);
            task = TaskManager.getInstance().addTask((e) -> runModel.timePlus(), 1000, "TIME");
            interrupt(Interrupt.PRINT_ALL_TASK);
            interrupt(Interrupt.PRINT_ALL_CONCEPT);
        } catch(Exception e) {
            log.error(e);
        }
        log.log("System core initiation complete.");
    }

    private void load() {
        log.log("construct game world...");
        String PATH = ConfigUtil.getValue("default.package");
        runModel = RunModel.getInstance();
        SCE sce = MapLoader.load(PATH);
        if (sce == null) {
            log.error("empty SCE file.");
            return;
        }
        runModel.setSentences(sce.getSentences());
        runModel.setMP(FileScanner.searchForMP(PATH, sce.getMap("main")));
        runModel.setCurrentPlayer(Player.getInstance());
        runModel.setStep(sce.getStart());
        Win.setRunModel();
        log.log("initial game world with \"" + sce.getName() + "\" settings...");
    }

    private void doNext() {
        BufferedScreen.write(runModel.getWords());
    }

    private void doAction(String buffer) {
        int keyCode;
        try {
            keyCode = Integer.parseInt(buffer);
        } catch(NumberFormatException nfe) {
            log.warning("input code not currently.");
            keyCode = 0;
        }
        switch(keyCode) {
            case KeyEvent.VK_UP:
                break;
        }
    }

    public void flush() {
        Win.flush();
    }

    /**
     * to use different interrupt codes, control this system.
     *
     * @param code interrupt code.
     */
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
     * 0x4a 接受键盘输入
     * 0x4b 进行当前动作
     * 0x9a 强制退出程序
     *
     * @param code interrupt code
     * @param buffer write into Buffer area
     */
    public static void interrupt(int code, String buffer) {
        log.log("interrupt code: " + code + " with buffer: " + (buffer == null ? "null" : buffer));
        switch (code) {
            case CLOSE_CURRENT_TASK:
                TaskManager.getInstance().close(dependence.task);
                break;
            case CLOSE_ALL_TASK:
                TaskManager.getInstance().closeAll();
                break;
            case PRINT_ALL_TASK:
                TaskManager.getInstance().printAll();
                break;
            case PRINT_ALL_CONCEPT:
                Proc.printAll();
                break;
            case WRITE_TO_BUFFER:
                BufferedScreen.write(buffer);
                break;
            case CLEAR_MESSAGE:
                break;
            case CLEAR_BUFFER:
                BufferedScreen.clear();
                break;
            case DO_NEXT:
                dependence.doNext();
                dependence.flush();
                break;
            case DO_ACTION:
                dependence.doAction(buffer);
                dependence.flush();
                break;
            case FORCE_EXIT:
                Exiter.exit();
                break;
        }
    }

}
