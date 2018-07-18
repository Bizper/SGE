package screen;


import service.TaskManager;
import types.StatusType;
import controller.dependence.Dependence;
import controller.model.RunModel;
import intf.Concept;
import intf.constant.DefaultConstant;
import controller.Exiter;
import intf.constant.Interrupt;
import service.AssetManager;
import service.Proc;
import util.DateUtil;
import util.Log;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Win extends Frame implements DefaultConstant {

    private static Log log = Log.getInstance(Win.class);

    private static Win win;

    private Image iBuffer;

    private Graphics gBuffer;

    private RunModel runModel;

    private void init() {
        log.log("initiate windows...");
        setBounds(200, 200, WIN_WIDTH, WIN_HEIGHT);
        setTitle("MUD GAME");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                log.log("closing windows...");
                Exiter.release();
            }
        });
        setVisible(true);
        addKeyListener(new KeySolution());
        setLayout(null);
        TaskManager.getInstance().addTask((e) -> render(), DefaultConstant.FRAME_PER_SECOND, "FLUSH");
        log.log("windows initiation complete.");
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        if(runModel == null) return;
        for(int i=1; i<MAX_MAP_SIZE_WIDTH; i++) {
            for(int j=2; j<MAX_MAP_SIZE_HEIGHT; j++) {
                Image image = AssetManager.getImage(runModel.getBlock(i, j).getImageName());
                if(image == null) continue;
                g.drawImage(image, i * DEFAULT_BLOCK_SIZE, j * DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, null);
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
            log.warning("not first of the call to this method, please do attention to this windows instance.");
        }
        return win;
    }

    private class KeySolution extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    Dependence.interrupt(Interrupt.PRINT_ALL_CONCEPT);
                    break;
            }
        }
    }


}
