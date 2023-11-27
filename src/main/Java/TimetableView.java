import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TimetableView extends Parent {

    private TimetableModel model = new TimetableModel();
    private ListView<TimetableEvent> listView = new ListView<>();

    public TimetableView() {
        listView.setPrefSize(300, 600);

        var btn = new CompoundButton(
                "Events for user",
                400, 50
        );
        btn.setOnAction(() -> {
            var userName = btn.textField.getText();
            var events = model.getEventsForUser(userName);
            listView.setItems(FXCollections.observableList(events));
        });

        var btn2 = new CompoundButton(
                "Events for room",
                400, 250
        );
        btn2.setOnAction(() -> {
            var roomName = btn2.textField.getText();
            var events = model.getEventsInRoom(roomName);
            listView.setItems(FXCollections.observableList(events));
        });

        getChildren().addAll(
                listView,
                btn,
                btn2
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
