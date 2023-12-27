import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;


public class TimetableView extends Parent {


    private final ListView<Student> listView = new ListView<>();


    public TimetableView() {

        listView.setPrefSize(300, 600);

        var btn = new CompoundButton(
                "Compile Records",
                400, 150
                );
        var btn2 = new CompoundButton(
                "Compile Records",
                400, 450
        );

        //this returns a complied record
        btn.setOnAction(() -> {
            List<Student> students = StudentRecordKt.recordStudent();
            listView.setItems(FXCollections.observableList(StudentRecordKt.getStudent()));

            //StudentRecordKt.recordStudent();
            //listView.setItems(FXCollections.observableList(Student));
        });
        //this searchs thrrough the firstnames and returns the record that has the first name
        btn2.setOnAction(() -> {
            var Names = btn2.textField.getText();
            var firstNames = StudentRecordKt.getfirstNames(Names);
        listView.setItems(FXCollections.observableList(firstNames));
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
