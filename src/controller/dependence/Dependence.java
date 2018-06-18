package controller.dependence;

import controller.Exiter;
import controller.model.RunModel;
import controller.scanner.FileScanner;
import controller.scanner.MapLoader;
import intf.DefaultConstant;
import screen.Win;
import service.AssetManager;
import service.TaskManager;
import impl.Player;
import mapping.SCE;
import screen.BufferedScreen;
import service.Proc;
import util.ConfigUtil;
import util.Log;

import static intf.Interrupt.*;

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
        AssetManager.init();
        this.load();
        task = TaskManager.getInstance().addTask((e) -> {
            flush();
        }, DefaultConstant.FRAME_PER_SECOND, "FLUSH");
        TaskManager.getInstance().addTask((e) -> {
            runModel.timePlus();
        }, 1000, "TIME");
        log.log("System core initiation complete.");
    }

    private void load() {
        log.log("start initial world...");
        log.log("construct game world...");
        runModel = RunModel.getInstance();
        SCE sce = MapLoader.load(ConfigUtil.getValue("default.package"));
        if (sce == null) {
            log.error("empty SCE file.");
            return;
        }
        runModel.setSentences(sce.getSentences());
        runModel.setMP(FileScanner.searchForMP(ConfigUtil.getValue("default.package"), sce.getMap("main")));
        runModel.setCurrentPlayer(Player.getInstance());
        runModel.setStep(sce.getStart());
        log.log("initial game world with \"" + sce.getName() + "\" settings...");
    }

    private void doNext() {
        BufferedScreen.write(runModel.getWords());
    }

    private void doAction() {

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
                dependence.doAction();
                dependence.flush();
                break;
            case FORCE_EXIT:
                Exiter.exit();
                break;
        }
    }

}
