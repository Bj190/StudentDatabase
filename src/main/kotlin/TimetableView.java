import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// @Author Bryn Jones 2023
// Version 1.0


public class TimetableView extends Parent {


    private final ListView<Student> listView = new ListView<>();


    public TimetableView() {

        listView.setPrefSize(300, 600);
        Map<String,Runnable> actions = new HashMap<>();
        TextField textField = new TextField();


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
        var btn7 = new CompoundButton(
                "Search by both First Name and Last Name",
                800, 200
        );

        //var sortingBtn = new CompoundButton(
                //"Sort by age",
                //400, 0
        //);

        var Dmenu = new Choicebox(
                "Search By", 800, 50
        );
        var writebtn = new CompoundButton(
                "Add new Student", 800, 200
        );
        var IDbtn = new CompoundButton(
                "Search By ID", 400, 950
        );



        //recheck the guidelines for good variable names
        //Upon Starting press this button to compile database into a record.
        btn.setOnAction(() -> {
            List<Student> students = StudentRecordKt.recordStudent();
            listView.setItems(FXCollections.observableList(StudentRecordKt.getStudent()));
        });
        Dmenu.setOnAction((selection, input) -> {
            switch (selection) {
                case "ID":
                    var MenuID = StudentRecordKt.getID(Integer.parseInt(input));
                    listView.setItems(FXCollections.observableList(MenuID));
                    break;
                case "First Name":
                    var MenufirstNames = StudentRecordKt.getfirstNames(input);
                    listView.setItems(FXCollections.observableList(MenufirstNames));
                    break;
                case "Last Name":
                    var MenulastNames = StudentRecordKt.getlastNames(input);
                    listView.setItems(FXCollections.observableList(MenulastNames));
                    break;
                case "Age":
                    var MenuAge = StudentRecordKt.getAge(Integer.parseInt(input));
                    listView.setItems(FXCollections.observableList(MenuAge));
                    break;
                case "Course Name":
                    var MenuCourseName = StudentRecordKt.getCourseName(input);
                    listView.setItems(FXCollections.observableList(MenuCourseName));
                    break;
                case "Course Module":
                    var MenuCourseModule = StudentRecordKt.getCourseModule(Integer.parseInt(input));
                    listView.setItems(FXCollections.observableList(MenuCourseModule));
                    break;
            }
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
        //This searches through the IDs and returns matches
        IDbtn.setOnAction(() -> {
            var btnID = Integer.parseInt(IDbtn.textField.getText()); //Int Expected
            var ID = StudentRecordKt.getID(btnID);
            listView.setItems(FXCollections.observableList(ID));
        });
        btn7.setOnAction(() -> {
            var btnCNames = btn7.textField.getText().split(" ");
            var firstName = btnCNames[0];
            var lastName = btnCNames[1];
            var combinedNames = StudentRecordKt.getCombinedNames(firstName, lastName);
            listView.setItems(FXCollections.observableList(combinedNames));
        });
        writebtn.setOnAction(() -> {
            String input = textField.getText();

            String[] parts = input.replace(" ", "").split(",");
            int ID = Integer.parseInt(parts[0]);
            String firstName = parts[1];
            String lastName = parts[2];
            int age = Integer.parseInt(parts[3]);
            String courseName = parts[4];
            int courseModule = Integer.parseInt(parts[5]);

            Student Studenttoadd = new Student(ID, firstName, lastName, age, courseName, courseModule);

            StudentRecordKt.addStudent(Studenttoadd);
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
                btn6,
                IDbtn,
                //btn7,
                writebtn,
                Dmenu
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

    //using the skeleton of the compound button and research combined both to create an improvement
    //this for some reason isn't adding to the stage and javafx application
    //realisation didn't need panel or frame as that creates an enterily different window when i want this added to the stage
    //so reordered code and deleted parts
    private static class Choicebox extends VBox {
        private Button btn = new Button();
        private ChoiceBox<String> ChoiceMenu = new ChoiceBox<>();
        private TextField textField = new TextField();
        String[] options = {"First Name", "Last Name", "Age", "Course Name", "Course Module"};

        Choicebox(String name, int x, int y) {
            btn.setFont(Font.font(34));
            btn.setText(name);
            ChoiceMenu.getItems().addAll(options);
            setTranslateX(x);
            setTranslateY(y);
            textField.setFont(Font.font(34));

            getChildren().addAll(btn, textField, ChoiceMenu);
        }
        //BiConsumer from lectures
        //put references and logic in this later
        void setOnAction(BiConsumer<String, String> action) {
            btn.setOnAction(e -> {
                String selection = ChoiceMenu.getValue();
                String input = textField.getText();
                if (selection != null && input != null) {
                    action.accept(selection, input);
                }
            });
        }
    }

}
