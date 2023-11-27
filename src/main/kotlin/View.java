import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var view = new TimetableView();

        stage.setScene(new Scene(view));
        stage.show();
    }

    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(View.class);
        }
    }
}
