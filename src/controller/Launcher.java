package controller;

import controller.task.TaskManager;
import javafx.stage.Stage;
import screen.Win;
import util.Log;

public class Launcher {

    private static Log log = Log.getInstance(Launcher.class);

    public static void main(String args[]) {
        log.log("game launcher started...");
        Dependence.launch();
        TaskManager.getInstance().addTimedTask((e) -> Win.start(), 5, "PRINT");
        log.log("initiation complete. start listening event message...");
    }

}
