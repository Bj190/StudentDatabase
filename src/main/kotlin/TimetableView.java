import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

// @Author Bryn Jones 2023
// Version 1.0


public class TimetableView extends Parent {


    private final ListView<Student> listView = new ListView<>();


    public TimetableView() {

        listView.setPrefSize(300, 600);

        var btn = new CompoundButton(
                "Compile Records",
                400, 50
                );
        var btn2 = new CompoundButton(
                "Search by First Names",
                400, 200
        );
        var btn3 = new CompoundButton(
                "Search by Last Names",
                400, 350
        );
        var btn4 = new CompoundButton(
                "Search by Age",
                400, 500
        );
        var btn5 = new CompoundButton(
                "Search by Course Name",
                400, 650
        );
        var btn6 = new CompoundButton(
                "Search by Course Module",
                400, 800
        );

        //var sortingBtn = new CompoundButton(
                //"Sort by age",
                //400, 0
        //);



        //recheck the guidelines for good variable names
        //Upon Starting press this button to compile database into a record.
        btn.setOnAction(() -> {
            List<Student> students = StudentRecordKt.recordStudent();
            listView.setItems(FXCollections.observableList(StudentRecordKt.getStudent()));
        });
        //this searches through the first names and returns the record that matches input
        btn2.setOnAction(() -> {
            var btnFNames = btn2.textField.getText();
            var firstNames = StudentRecordKt.getfirstNames(btnFNames);
        listView.setItems(FXCollections.observableList(firstNames));
        });
        //this searches through the last names and returns the record that matches input
        btn3.setOnAction(() -> {
            var btnLNames = btn3.textField.getText();
            var lastNames = StudentRecordKt.getlastNames(btnLNames);
            listView.setItems(FXCollections.observableList(lastNames));
        });
        //this searches through the ages and returns the record that matches input
        //Some way to only allow ints to be accepted?
        btn4.setOnAction(() -> {
            var btnAge = Integer.parseInt(btn4.textField.getText()); //Int Expected
            var ages = StudentRecordKt.getAge(btnAge);
            listView.setItems(FXCollections.observableList(ages));
        });
        //this searches through the course names and returns the record that matches input
        btn5.setOnAction(() -> {
            var btnCName = btn5.textField.getText();
            var courseName = StudentRecordKt.getCourseName(btnCName);
            listView.setItems(FXCollections.observableList(courseName));
        });
        //this searches through the course module number and returns the record that matches input
        btn6.setOnAction(() -> {
            var btnCModule = Integer.parseInt(btn6.textField.getText()); //Int Expected
            var courseModule = StudentRecordKt.getCourseModule(btnCModule);
            listView.setItems(FXCollections.observableList(courseModule));
        });
       //sortingBtn.setOnAction(() -> {
            //right idea wrong logic
            //listView.getItems().set() //this is not proper. This is a work around It complies a new record but sorted.
            //first step
            //This needs to sort by age(oldest to youngest)
            //prerequists: record needs to be complied. ListView must contain entries.
        //});


        getChildren().addAll(
                listView,
                btn,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6
                //sortingBtn
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
