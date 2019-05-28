package screen;


import controller.model.ImageDO;
import resources.Strings;
import service.TaskManager;
import types.StatusType;
import controller.dependence.Dependence;
import controller.model.RunModel;
import intf.Concept;
import intf.constant.DefaultConstant;
import controller.Exiter;
import intf.constant.Interrupt;
import service.Proc;
import util.Log;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Win extends Frame implements DefaultConstant, Strings {

    private static Log log = Log.getInstance(Win.class);

    private static Win win;

    private Image iBuffer;

    private ArrayList<ImageDO> image_list = new ArrayList<>();

    private Graphics gBuffer;

    private RunModel runModel;

    private void init() {
        log.log(initiate_windows);
        setBounds(200, 200, WIN_WIDTH, WIN_HEIGHT);
        setTitle(window_title);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                log.log(closing_windows);
                Exiter.release();
            }
        });
        setVisible(true);
        addKeyListener(new KeySolution());
        setLayout(null);
        TaskManager.getInstance().addTask((e) -> render(), DefaultConstant.FRAME_PER_SECOND, "FLUSH");
        log.log(windows_initiation_complete);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        if(runModel == null) return;
        g.drawImage(runModel.getMap(), DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, MAX_MAP_SIZE_WIDTH, MAX_MAP_SIZE_HEIGHT, null);
        if(image_list.size() > 0) {
            for(ImageDO image : image_list) {
                g.drawImage(image.getImage(), image.getX(), image.getY(), image.getWidth(), image.getHeight(), null);
            }
        }
        Proc.conceptFilterSet(this::drawableCheck).forEach((e) -> e.paint(g));
    }

    private boolean drawableCheck(Concept concept) {
        return concept.getStatus() != StatusType.STATUS_NON_DRAWABLE || concept.getStatus() != StatusType.STATUS_NON_SHOW;
    }

    public void update(Graphics scr) {
        if(iBuffer == null) {
            iBuffer=createImage(this.getSize().width, this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
        paint(gBuffer);
        scr.drawImage(iBuffer, 0, 0, this);
    }

    private void render() {
        if(runModel != null) runModel.checkStatus();
        repaint();
    }

    public static void flush() {
        win.render();
    }

    public static void setRunModel() {
        win.runModel = RunModel.getInstance();
    }

    public static Win launch() {
        if(win == null) {
            win = new Win();
            win.init();
        }
        else {
            log.warning(warning_windows_instance);
        }
        return win;
    }

    private class KeySolution extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    Dependence.interrupt(Interrupt.FORCE_EXIT);
                    break;
                default:
                    Dependence.interrupt(Interrupt.PRESS_KEY, e.getKeyCode());
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                default:
                    Dependence.interrupt(Interrupt.RELEASE_KEY, e.getKeyCode());
                    break;
            }
        }
    }


}
