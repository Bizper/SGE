package controller;

import controller.task.TaskManager;
import javafx.stage.Stage;
import screen.Win;
import util.Log;

public class Launcher {

    private static Log log = Log.getInstance(Launcher.class);

    public static void main(String args[]) {
        log.log("game launcher started...");
        Win.launch();
        Dependence.launch();
        log.log("initiation complete. start listening event message...");
    }

}
