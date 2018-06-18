package screen;


import intf.DefaultConstant;
import controller.model.RunModel;
import util.DateUtil;
import util.Log;

import java.awt.*;

public class Display {

    private static Log log = Log.getInstance(Display.class);

    private static Display display;

    private Panel panel;

    private Display(Panel p) {
        this.panel = p;
        init();
    }

    public static Display getInstance() {
        if(display == null) return null;
        return display;
    }

    public static Display getInstance(Panel panel) {
        if(display == null) display = new Display(panel);
        return display;
    }

    private Graphics g;

    private void init() {
        log.log("initiate Display Panel...");
        g = panel.getGraphics();
        log.log("Display Panel initiation complete.");
    }
    /**
     * draw images on screen.
     * @param image which pics you want to draw
     * @param x location x
     * @param y location y
     */
    private void DRAW_IMAGE(Image image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public static void STATIC_DRAW_IMAGE(Image image, int x, int y) {
        display.DRAW_IMAGE(image, x, y);
    }

    public static void STATIC_DRAW_IMAGE(Image image) {
        STATIC_DRAW_IMAGE(image, 0, 0);
    }
    //绘制字符串在屏幕上
    private void DRAW_STRING(String s, int x, int y) {
        g.drawString(s, x, y);
    }

    public static void STATIC_DRAW_STRING(String s, int x, int y) {
        display.DRAW_STRING(s, x, y);
    }
    //清屏
    private void CLEAR_SCREEN() {
        g.clearRect(0, 0, DefaultConstant.WIN_WIDTH, DefaultConstant.WIN_HEIGHT);
    }

    public static void STATIC_CLEAR_SCREEN() {
        display.CLEAR_SCREEN();
    }
    //接受一个runModel实例并将其绘制在屏幕上
    private static void render(RunModel runModel) {
        STATIC_CLEAR_SCREEN();
        STATIC_DRAW_STRING("游戏时间：" + DateUtil.getTime(runModel.getTime()), 15, 20);
        STATIC_DRAW_IMAGE(runModel.getBackground());
    }
    //刷新
    public void FLUSH() {
        render(RunModel.getInstance());
    }
    public static void STATIC_FLUSH() {
        display.FLUSH();
    }


}
