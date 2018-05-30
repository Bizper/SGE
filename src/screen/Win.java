package screen;

import constant.DefaultConstant;
import controller.Dependence;
import controller.Exiter;
import util.Log;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Win extends Frame implements DefaultConstant {

    private static Win win;

    private static Log log = Log.getInstance(Win.class);

    private EventSolution eventSolution = new EventSolution();
    private KeySolution keySolution = new KeySolution();

    private Panel panel = new Panel();

    private Win() {
        init();
    }

    public static Win launch() {
        if(win == null) win = new Win();
        return win;
    }

    public void init() {
        log.log("initial windows...");
        setBounds(WIN_X, WIN_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle(DEFAULT_TITLE);
        setResizable(false);
        setVisible(true);
        addWindowListener(eventSolution);
        addKeyListener(keySolution);
        panel.setBounds(0, 0, WIN_WIDTH, WIN_HEIGHT);
        add(panel);
        Display.getInstance(panel);
        log.log("windows initiation complete.");
    }

    private static class EventSolution extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            log.log("closing windows...");
            win.setVisible(false);
            Exiter.release();
        }
    }

    private static class KeySolution extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    Dependence.interrupt(0x4a);
                case KeyEvent.VK_F1:
                    Dependence.interrupt(0x4e);
            }
        }
    }

}
