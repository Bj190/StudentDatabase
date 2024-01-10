import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.*;
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
                400, 950
        );

        var CBox = new Choicebox(
                "Search By", 800, 50
        );
        var writebtn = new CompoundButton(
                "Add new Student", 800, 200
        );
        var IDbtn = new CompoundButton(
                "Search By ID", 400, 950
        );

        var FNsortbtnA = new TextlessButton(
                "Sort by Ascending Names", 800, 350
        );
        var FNsortbtnD = new TextlessButton(
                "Sort by Descending Names", 800, 500
        );
        var MarksSortBtnL= new TextlessButton(
                "Sort by Lowest Marks", 800, 650
        );
        var MarksSortBtnH= new TextlessButton(
                "Sort by Highest Marks", 800, 800
        );
        var Removebtn = new CompoundButton(
                "Remove Student", 400, 1100
        );



        //recheck the guidelines for good variable names
        //Upon Starting press this button to compile database into a record.
        //Complie Record Button
        btn.setOnAction(() -> {
            List<Student> students = StudentRecordKt.recordStudent();
            listView.setItems(FXCollections.observableList(StudentRecordKt.getStudent()));
        });

        //Choice Box
        CBox.setOnAction((selection, input) -> {
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
                case "Marks":
                    var MenuMarks = StudentRecordKt.getMark(Integer.parseInt(input));
                    listView.setItems(FXCollections.observableList(MenuMarks));
                    break;
            }
        });
        //Get Buttons

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

        //This is a combined Search button and function needs further research and testing
        btn7.setOnAction(() -> {
            var btnCNames = btn7.textField.getText().split(" ");
            var firstName = btnCNames[0];
            var lastName = btnCNames[1];
            System.out.println("Before calling getCombinedNames: firstName=$firstName, lastName=$lastName");
            var combinedNames = StudentRecordKt.getCombinedNames(firstName, lastName);

            if (combinedNames.isEmpty()) {
                System.out.println("No students found with the provided first name and last name.");
            } else {
                System.out.println("Found ${combinedNames.size} students with the provided first name and last name.");
                listView.setItems(FXCollections.observableList(combinedNames));
            }
        });

        //Sorting Buttons

        //this sorts through the Firstnames in Ascending order
        //this functionaily works but it still has the textfield so somehow have to remove that so remember to do that
        //this makes a new list instead of sorting the current list need to find out how to make it so it doesn't make a new one but sorts the current list
        FNsortbtnA.setOnAction(() -> {
            List<Student> students = new ArrayList<>(listView.getItems());
            students.sort(Comparator.comparing(Student::getFirstName));
            listView.setItems(FXCollections.observableList(students));
        });
        FNsortbtnD.setOnAction(() -> {
            List<Student> students = new ArrayList<>(listView.getItems());
            students.sort(Comparator.comparing(Student::getFirstName).reversed());
            listView.setItems(FXCollections.observableList(students));
        });
        //Sorts marks in ascending order so lowest number first
        MarksSortBtnL.setOnAction(() -> {
            List<Student> students = new ArrayList<>(listView.getItems());
            students.sort(Comparator.comparing(Student:: getMark));
            listView.setItems(FXCollections.observableList(students));
        });
        //Sorts marks in Descending order so highest number first
        MarksSortBtnH.setOnAction(() -> {
            List<Student> students = new ArrayList<>(listView.getItems());
            students.sort(Comparator.comparing(Student:: getMark).reversed());
            listView.setItems(FXCollections.observableList(students));
        });
        //Write Button.
        //To be tidied up and finished
        //this writes to file but it's not showing up in the tableview when you reload it?
        writebtn.setOnAction(() -> {
            String input = writebtn.textField.getText().trim();
            System.out.println("Input: " + input);

            String[] parts = input.replace(" ", "").split(",");
            if (parts.length != 7 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty() || parts[3].isEmpty() || parts[4].isEmpty() || parts[5].isEmpty() || parts[6].isEmpty()) {
                System.out.println("Invalid Input");
                return;
            }
            int ID = Integer.parseInt(parts[0]);
            String firstName = parts[1];
            String lastName = parts[2];
            int age = Integer.parseInt(parts[3]);
            String courseName = parts[4];
            int courseModule = Integer.parseInt(parts[5]);
            int mark = Integer.parseInt(parts[6]);

            Student studentToAdd = new Student(ID, firstName, lastName, age, courseName, courseModule, mark);
            System.out.println("Student to be added " + studentToAdd);

            StudentRecordKt.addStudent(studentToAdd);
            System.out.println("Student was added.");
        });
        //Remove Button
        Removebtn.setOnAction(() -> {
            System.out.println("Button clicked"); // Debug

            String input = Removebtn.textField.getText().trim();
            System.out.println("Input: " + input);

            String[] parts = input.replace(" ", "").split(",");
            if (parts.length != 7 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty() || parts[3].isEmpty() || parts[4].isEmpty() || parts[5].isEmpty() || parts[6].isEmpty()) {
                System.out.println("Invalid Input");
                return;
            }
            int ID = Integer.parseInt(parts[0]);
            String firstName = parts[1];
            String lastName = parts[2];
            int age = Integer.parseInt(parts[3]);
            String courseName = parts[4];
            int courseModule = Integer.parseInt(parts[5]);
            int mark = Integer.parseInt(parts[6]);

            Student StudentToBeRemoved = new Student(ID, firstName, lastName, age, courseName, courseModule, mark);
            System.out.println("Student to be removed " + StudentToBeRemoved);
            StudentRecordKt.removeStudent(StudentToBeRemoved);
        });

        //Adding Buttons
        getChildren().addAll(
                listView,
                btn,
                btn2,
                btn3,
                btn4,
                btn5,
                btn6,
                IDbtn,
                FNsortbtnA,
                FNsortbtnD,
                MarksSortBtnL,
                MarksSortBtnH,
                Removebtn,
                btn7,
                writebtn,
                CBox
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

    private static class TextlessButton extends VBox {
        private Button btn = new Button();

        TextlessButton(String name, int x, int y) {
            btn.setFont(Font.font(34));
            btn.setText(name);


            setTranslateX(x);
            setTranslateY(y);

            getChildren().addAll(btn);
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
        String[] options = {"ID","First Name", "Last Name", "Age", "Course Name", "Course Module","Marks"};

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
