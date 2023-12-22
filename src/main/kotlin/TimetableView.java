import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class TimetableView extends Parent {


    private final ListView<Student> listView = new ListView<>();
    ObservableList<String> Student = FXCollections.observableArrayList(StudentRecordKt.Student);

    public TimetableView() {

        listView.setPrefSize(300, 600);

        var btn = new CompoundButton(
                "Compile Records",
                400, 150
                );

        var btn2 = new CompoundButton(
                "Get Students",
                400, 350
        );


        btn.setOnAction(() -> {

            StudentRecordKt.recordStudent();
            listView.setItems(Student);
        });
        btn2.setOnAction(() -> {
        var studentNames = btn2.textField.getText();
        var studentNamesDatabase = //getStudentFirstNames(studentNames);
        listView.setItems(FXCollections.observerableList(studentNamesDatabase));
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
