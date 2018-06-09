package screen;

import constant.DefaultConstant;
import controller.Exiter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Log;

public class Win extends Application implements DefaultConstant {

    private static Log log = Log.getInstance(Win.class);

    @Override
    public void start(Stage stage) {
        log.log("initial windows...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/settings/main.fxml"));
        Pane p = null;
        try {
            p = loader.load();
        } catch (Exception e) {
            log.error("windows initiation failed. Can not load Graphics setting file.");
            Exiter.exit(Win.class);
        }
        Scene main = new Scene(p, WIN_WIDTH, WIN_HEIGHT);
        stage.setScene(main);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            log.log("closing windows...");
            stage.hide();
            Exiter.release();
        });
        stage.setTitle(DEFAULT_TITLE);
        stage.show();
        Display display = loader.getController();
        display.setInstance(display);
        log.log("windows initiation complete.");
    }

    public static void start() {
        Win.launch();
    }

}
