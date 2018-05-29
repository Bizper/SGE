package screen;

import util.Log;

import java.awt.*;

public class Display {

    private static Log log = Log.getInstance(Display.class);

    private static Display display;

    private Panel panel;

    private Label location = new Label("LOCATION:");
    private Label status = new Label("STATUS:");
    private TextArea message = new TextArea("", 600, 500, TextArea.SCROLLBARS_NONE);

    private Display(Panel panel) {
       this.panel = panel;
       init();
    }

    private void init() {
        log.log("initial " + Display.class.getName());

        location.setBounds(10, 10, 100, 10);
        status.setBounds(10, 30, 100, 10);
        message.setBounds(10, 50, 600, 400);
        message.setEditable(false);
        panel.setLayout(null);
        panel.add(location);
        panel.add(status);
        panel.add(message);

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

    public Display setLocation(String title) {
        location.setText("LOCATION:"+title);
        return this;
    }

    public Display append(String str) {
        message.append(BufferedScreen.add(str + "\n"));
        return this;
    }

}
