package screen;

import controller.Dependence;
import controller.model.RunModel;
import util.Log;

import java.awt.*;

public class Display {

    private static Log log = Log.getInstance(Display.class);

    private static Display display;

    private Panel panel;

    private Label location = new Label("LOCATION：");
    private Label status = new Label("STATUS：");
    private Label player = new Label("PLAYER：");

    private Button next = new Button("NEXT");
    private Button action = new Button("ACTION");

    private TextArea message = new TextArea("", 600, 500, TextArea.SCROLLBARS_NONE);

    private Display(Panel panel) {
       this.panel = panel;
       init();
    }

    private void init() {
        log.log("initial " + Display.class.getName());

        location.setBounds(10, 10, 600, 10);
        status.setBounds(10, 30, 600, 10);
        player.setBounds(620, 10, 300, 10);
        message.setBounds(10, 50, 600, 400);
        next.setBounds(10, 470, 50, 20);
        action.setBounds(110, 470, 50, 20);
        next.addActionListener((e) -> Dependence.interrupt(0x4a));
        action.addActionListener((e) -> Dependence.interrupt(0x4b));
        message.setEditable(false);
        panel.setLayout(null);
        panel.add(location);
        panel.add(player);
        panel.add(status);
        panel.add(message);
        panel.add(next);
        panel.add(action);

        log.log(Display.class.getName() + " initiation complete.");
    }

    public static Display getInstance(Panel panel) {
        if(display == null) display = new Display(panel);
        return display;
    }

    public static Display getInstance() {
        if(display == null) return null;
        return display;
    }

    public Display clear() {
        message.setText("");
        return this;
    }

    public Display setLocation(String title) {
        location.setText("LOCATION："+title);
        return this;
    }

    public Display setAction(String str) {
        action.setLabel(str);
        return this;
    }

    public Display setNext(String str) {
        next.setLabel(str);
        return this;
    }

    public Display append(String str) {
        message.append(str + "\n");
        return this;
    }

    public Display append(RunModel runModel) {
        setLocation(runModel.getCurrentLocation());
        player.setText("PLAYER：" + runModel.getCurrentPlayer().getName());
        append();
        return this;
    }

    public Display append() {
        if(BufferedScreen.isChange()) {
            message.append(BufferedScreen.get() + "\n");
        }
        return this;
    }

}
