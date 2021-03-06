package controller.dependence;

import controller.Exiter;
import controller.model.RunModel;
import controller.scanner.FileScanner;
import controller.scanner.MapLoader;
import intf.constant.Interrupt;
import resources.Strings;
import screen.Win;
import service.*;
import impl.Player;
import impl.person.Character;
import mapping.SCE;
import screen.BufferedScreen;
import util.ConfigUtil;
import util.Log;

import java.awt.event.KeyEvent;

import static intf.constant.Interrupt.*;

/**
 * 整个游戏系统的核心，称为依赖(Dependence)。
 * 通过使用中断(interrupt)方法来控制整个程序的运行。
 */
public class Dependence implements Strings {

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
        log.log(initiate_system_core);
        try {
            this.load();
            AudioManager.load("cyper party", true);
            task = TaskManager.getInstance().addTask((e) -> runModel.timePlus(), 1000, "TIME");//计数器方法
            Proc.printAll();
        } catch(Exception e) {
            log.error(e);
        }
        log.log(initiate_system_core_complete);
    }

    private void load() {
        log.log(construct_game_world);
        String PATH = ConfigUtil.getValue("default.package");
        runModel = RunModel.getInstance();
        SCE sce = MapLoader.load(PATH);
        if (sce == null) {
            log.error(empty_sce_error);
            return;
        }
        runModel.setSentences(sce.getSentences());
        runModel.setMP(FileScanner.searchForMP(PATH, sce.getMap("main")));
        Character character = ConceptFactory.getInstance(Character.class);
        character.setLocation(20, 400);
        character.setName("RainNeverOver");
        runModel.setCurrentCharacter(character);
        runModel.setCurrentPlayer(Player.getInstance());
        runModel.setStep(sce.getStart());
        Win.setRunModel();
        log.log("基于 \"" + sce.getName() + "\" 设置初始化游戏世界完成...");
    }

    private void doAction(String buffer, boolean flag) {
        int keyCode;
        try {
            keyCode = Integer.parseInt(buffer);
        } catch(NumberFormatException nfe) {
            log.warning(input_code_error);
            keyCode = 0;
        }
        switch(keyCode) {
            case KeyEvent.VK_F1:
                if(flag) interrupt(PRINT_ALL_TASK);
                break;
            case KeyEvent.VK_UP:
                runModel.setMove_up(flag);
                break;
            case KeyEvent.VK_LEFT:
                runModel.setMove_left(flag);
                break;
            case KeyEvent.VK_DOWN:
                runModel.setMove_down(flag);
                break;
            case KeyEvent.VK_RIGHT:
                runModel.setMove_right(flag);
                break;
        }
    }

    private void flush() {
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
     * 0x4b 释放按键
     * 0x7a 刷新屏幕
     * 0x9a 强制退出程序
     *
     * @param code interrupt code
     * @param buffer write into Buffer area
     */
    public static void interrupt(int code, Object buffer) {
        if(buffer == null) buffer = "";
        switch (code) {
            case CLOSE_CURRENT_TASK:
                TaskManager.getInstance().close(dependence.task);
                break;
            case CLOSE_ALL_TASK:
                TaskManager.getInstance().closeAll();
                break;
            case PRINT_ALL_TASK:
                TaskManager.printAll();
                break;
            case PRINT_ALL_CONCEPT:
                Proc.printAll();
                break;
            case WRITE_TO_BUFFER:
                BufferedScreen.write(buffer.toString());
                break;
            case CLEAR_MESSAGE:
                break;
            case CLEAR_BUFFER:
                BufferedScreen.clear();
                break;
            case PRESS_KEY:
                dependence.doAction(buffer.toString(), true);
                interrupt(Interrupt.FLUSH_SCREEN);
                break;
            case RELEASE_KEY:
                dependence.doAction(buffer.toString(), false);
                interrupt(Interrupt.FLUSH_SCREEN);
                break;
            case FLUSH_SCREEN:
                dependence.flush();
                break;
            case FORCE_EXIT:
                Exiter.exit();
                break;
        }
    }

}
