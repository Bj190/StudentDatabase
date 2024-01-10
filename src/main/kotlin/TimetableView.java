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

        //Creating Record
        var btn = new CompoundButton(
                "Compile Records",
                400, 50
        );
        //Combined Search
        var btn7 = new CompoundButton(
                "Search by both First Name and Last Name",
                400, 950
        );
        //Choice Box for searches
        var CBox = new Choicebox(
                "Search By", 350, 50
        );
        //Add and Remove
        var writebtn = new CompoundButton(
                "Add new Student", 800, 50
        );
        var Removebtn = new CompoundButton(
                "Remove Student", 800, 200
        );
        //Sorting
        var FNsortbtnA = new (
                "Sort by Ascending Names", 350, 200
        );
        var FNsortbtnD = new CompoundButton(
                "Sort by Descending Names", 800, 500
        );
        var MarksSortBtnL= new CompoundButton(
                "Sort by Lowest Marks", 800, 650
        );
        var MarksSortBtnH= new CompoundButton(
                "Sort by Highest Marks", 800, 800
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
        //Get Combined Names
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
