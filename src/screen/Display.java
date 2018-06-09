package screen;

import controller.Dependence;
import controller.model.RunModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import util.Log;

public class Display {

    private static Log log = Log.getInstance(Display.class);

    private static Display display;

    void setInstance(Display display) {
        Display.display = display;
    }

    @FXML
    private Label location;

    @FXML
    private TextArea script_msg;

    @FXML
    private TextArea battle_msg;

    @FXML
    private Label game_time;

    @FXML
    private Label desc;

    @FXML
    private Label operation;

    @FXML
    private ProgressBar progress;

    @FXML
    public void doNext(ActionEvent e) {
        Dependence.interrupt(0x4a);
    }

    @FXML
    public void doAction(ActionEvent e) {

    }

    void apendScript(String str) {
        script_msg.appendText(str);
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

    //range from 0.0 - 1.0
    public void setProgress(double proc, String opr) {
        progress.setProgress(proc);
        operation.setText(opr);
    }

    public static void render(RunModel runModel) {
        display.setLocation(runModel.getCurrentMp().getName());
        display.setTime(runModel.getTime());
        if(BufferedScreen.isChange()) display.apendScript(BufferedScreen.get());
    }

}
