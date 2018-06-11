package screen;


import controller.Dependence;
import controller.model.RunModel;
import util.DateUtil;
import util.Log;

import java.awt.*;
import java.net.URLEncoder;

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

    private Label location = new Label();
    private Label location_label = new Label("Location：");
    private Label game_time = new Label();
    private Label game_time_label = new Label("GameTime：");
    private Label desc = new Label();
    private TextArea script_msg = new TextArea();
    private TextArea battle_msg = new TextArea();

    private Button next = new Button("NEXT");
    private Button action = new Button("ACTION");

    private void init() {
        log.log("initiate Display Panel...");
        location_label.setBounds(14, 14, 70, 30);
        location.setBounds(84, 14, 100, 30);
        game_time_label.setBounds(14, 44, 70, 30);
        game_time.setBounds(84, 44, 60, 30);
        script_msg.setBounds(14, 79, 777, 296);
        battle_msg.setBounds(811, 79, 453, 296);
        next.setBounds(1114, 670, 40, 30);
        action.setBounds(939, 670, 40, 30);
        next.addActionListener((e) -> doNext());
        action.addActionListener((e) -> doAction());
        panel.add(next);
        panel.add(action);
        panel.add(location_label);
        panel.add(location);
        panel.add(battle_msg);
        panel.add(game_time);
        panel.add(script_msg);
        panel.add(game_time_label);
        log.log("Display Panel initiation complete.");
    }

    public void doNext() {
        Dependence.interrupt(0x4a);
    }

    public void doAction() {
        Dependence.interrupt(0x4b);
    }

    void appendScript(String str) {
        script_msg.append(str + "\n");
    }

    void appendBattle(String str) {
        battle_msg.append(str + "\n");
    }

    public void setLocation(String str) {
        location.setText(str);
    }

    public void setTime(String str) {
        game_time.setText(str);
    }

    public void setDesc(String str) {
        desc.setText(str);
    }

    public static void render(RunModel runModel) {
        display.setLocation(runModel.getCurrentMp().getName());
        display.setTime(DateUtil.getTime(runModel.getTime()));
        if(BufferedScreen.isChange()) display.appendScript(BufferedScreen.get());
    }

    public void flush() {
        render(RunModel.getInstance());
    }

    public void clear() {
        script_msg.setText("");
        battle_msg.setText("");
    }

}
