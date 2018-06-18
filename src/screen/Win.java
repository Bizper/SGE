package screen;


import intf.DefaultConstant;
import controller.Exiter;
import util.Log;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Win extends Frame implements DefaultConstant {

    private static Log log = Log.getInstance(Win.class);

    private static Win win;

    private Image iBuffer;

    private Graphics gBuffer;

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
        Panel panel = new Panel();
        panel.setBounds(0, 0, WIN_WIDTH, WIN_HEIGHT);
        panel.setLayout(null);
        add(panel);
        Display.getInstance(panel);
        log.log("windows initiation complete.");
    }

    public void update(Graphics scr) {
        if(iBuffer==null) {
            iBuffer=createImage(this.getSize().width, this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
        paint(gBuffer);
        scr.drawImage(iBuffer, 0, 0,this);
    }

    public static void launch() {
        if(win == null) {
            win = new Win();
            win.init();
        }
        else log.warning("not first of this method called, please warning this windows instance.");
    }


}
