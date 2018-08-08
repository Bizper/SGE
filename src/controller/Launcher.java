package controller;

import controller.dependence.Dependence;
import screen.Win;
import service.AssetManager;
import util.Log;

public class Launcher {

    private static Log log = Log.getInstance(Launcher.class);

    public static void main(String args[]) {
        log.log("游戏启动器开始启动...");
        Win.launch();
        AssetManager.init();//调用资源管理器的初始化方法
        Dependence.launch();
        log.log("初始化过程完毕，开始监听事件...");
    }

}
