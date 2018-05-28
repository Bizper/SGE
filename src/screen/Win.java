package screen;

import constant.DefaultConstant;
import controller.Exiter;
import controller.TaskManager;
import util.ConfigUtil;
import util.Log;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Win extends Frame implements DefaultConstant {

    private static Win win;

    private static Log log = Log.getInstance(Win.class);

    private EventSolution eventSolution = new EventSolution();

    private Panel panel = new Panel();

    private Win() {}

    public static Win launch() {
        if(win == null) win = new Win();
        win.init();
        return win;
    }

    public void init() {
        log.log("initial windows...");
        setBounds(WIN_X, WIN_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle(DEFAULT_TITLE);
        setResizable(false);
        setVisible(true);
        addWindowListener(eventSolution);
        add(panel);
    }

    private static class EventSolution extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            log.log("closing windows...");
            win.setVisible(false);
            Exiter.release();
        }

    }

}
