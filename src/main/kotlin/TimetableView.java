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
                "Search by First Names",
                400, 350
        );
        var btn3 = new CompoundButton(
                "Search by Last Names",
                400, 550
        );
        var btn4 = new CompoundButton(
                "Search by Age",
                400, 750
        );
        var btn5 = new CompoundButton(
                "Search by Course Name",
                400, 950
        );
        var btn6 = new CompoundButton(
                "Search by Course Module",
                400, 1150
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
            var btnFNames = btn2.textField.getText();
            var firstNames = StudentRecordKt.getfirstNames(btnFNames);
        listView.setItems(FXCollections.observableList(firstNames));
        });
        //recheck the guidelines for good variable names
        btn3.setOnAction(() -> {
            var btnLNames = btn3.textField.getText();
            var lastNames = StudentRecordKt.getlastNames(btnLNames);
            listView.setItems(FXCollections.observableList(lastNames));
        });
        btn4.setOnAction(() -> {
            var btnAge = btn4.textField.getText();  //needs to be an int getText expects a String
            var ages = StudentRecordKt.getAge(btnAge);
            listView.setItems(FXCollections.observableList(ages));
        });
        btn5.setOnAction(() -> {
            var btnCName = btn3.textField.getText();
            var courseName = StudentRecordKt.getlastNames(btnCName);
            listView.setItems(FXCollections.observableList(courseName));
        });
        btn6.setOnAction(() -> {
            var btnCModule = btn3.textField.getText(); //needs to be an int getText expects a String
            var courseModule = StudentRecordKt.getlastNames(btnCModule);
            listView.setItems(FXCollections.observableList(courseModule));
        });


        getChildren().addAll(
                listView,
                btn,
                btn2,
                btn3,
                btn4
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
