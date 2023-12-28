import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class View extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var view = new TimetableView();
        ScrollPane sp = new ScrollPane(); //not sure how this works yet further research

        stage.setScene(new Scene(view));
        stage.setResizable(true);
        stage.setHeight(1500); //this is not suitable for different screens //maybe get width and height of screen then divide?
        stage.setWidth(1500);
        stage.;
        stage.show();

    }

    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(View.class);
        }
    }
}
