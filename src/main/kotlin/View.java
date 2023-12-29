import javafx.application.Application;
import javafx.geometry.Rectangle2D; // new
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.awt.*;

public class View extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var view = new TimetableView();
        Rectangle2D screen = Screen.getPrimary().getVisualBounds(); //new
        ScrollPane sp = new ScrollPane(); //not sure how this works yet further research is supposed to make the stage scrollable
        sp.setContent(view);
        sp.setMinHeight(200);

        Scene scene = new Scene(sp, screen.getWidth(), screen.getHeight()); //this adds the scrollbar alongside the screen //see scrollpane in notes
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setHeight(screen.getHeight()); //this is not suitable for different screens //maybe get width and height of screen then divide?
        stage.setWidth(screen.getWidth()); // trying to get the screen resolution of the whichever device it loads and then adjust it
        //this section to be tested on different screen types. Use University Computers
        stage.setX(screen.getMinX()); //+ (screen.getWidth() - stage.getWidth())); //new
        stage.setY(screen.getMinY());//+ (screen.getHeight() - stage.getHeight())); //new
        //scroll pane Code: sp.setContent(View)?

        stage.hide();
        stage.show();

    }

    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(View.class);
        }

    }
}
