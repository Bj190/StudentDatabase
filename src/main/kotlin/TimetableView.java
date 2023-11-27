import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TimetableView extends Parent {

    private ListView<Student> listView = new ListView<>();

    public TimetableView() {

        listView.setPrefSize(300, 600);

        var btn = new CompoundButton(
                "Get Students",
                400, 50
        );
        btn.setOnAction(() -> {
            var userName = btn.textField.getText();
            var studentsList = //needs to be the action when pressing get students so it should show the students
        });

        getChildren().addAll(
                listView,
                btn
        );
    }

    private static class CompoundButton extends VBox {
        private Button btn = new Button();
        private TextField textField = new TextField();

        CompoundButton(String name, int x, int y) {
            btn.setFont(Font.font(34));
            btn.setText(name);

            textField.setFont(Font.font(34));

            setTranslateX(x);
            setTranslateY(y);

            getChildren().addAll(btn, textField);
        }

        void setOnAction(Runnable action) {
            btn.setOnAction(e -> action.run());
        }
    }
}
