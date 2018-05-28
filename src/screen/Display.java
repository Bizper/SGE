package screen;

import java.awt.*;

public class Display {

    private static Display display;

    private Panel panel;

    private Label label = new Label();

    public static Display getInstance() {
        if(display == null) display = new Display();
        return display;
    }

    public Display setTitle(String title) {
        label.setText(title);
        return this;
    }

    public Display setBasePanel(Panel panel) {
        this.panel = panel;
        return this;
    }

}
