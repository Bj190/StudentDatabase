import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

// @Author Bryn Jones 2023
// Version 1.0


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use
data class Student(val ID: Int, val firstName: String, val  lastName: String, val age: Int, val courseName: String, val courseModule: Int, val Mark: Int)
fun recordStudent(): MutableList<Student>{
    //This will read the file then if the data is stored correctly will read it and then convert into a record for the software to manipulate
    //@JvmRecord1
    val file = Paths.get("StudentDatabase.txt")
    val studentLines = Files.readAllLines(file)
    println("File content:")
    studentLines.forEach { println(it) }
    return studentLines.map {line ->
        val tokens = line.replace(" ", "").split(",") //replace line.trim() with line.replace (" ", "") //question if works
        Student(ID = tokens[0].toInt(), firstName = tokens[1], lastName = tokens[2], age = tokens[3].toInt(), courseName = tokens[4], courseModule = tokens[5].toInt(), Mark = tokens[6].toInt())
    }.toMutableList(); //this was orignically a list which you can't add to.
}

var students: List<Student> = emptyList()
//Add student needs to take in the same info as a Student data class have the current record open and then write that into the file.
fun addStudent(newStudent: Student) {
    println("Adding student: $newStudent")
    val students = recordStudent()
    students.add(newStudent)
    val file = Paths.get("StudentDatabase.txt")
    val studentData = students.joinToString("\n") { "${it.ID},${it.firstName},${it.lastName},${it.age},${it.courseName},${it.courseModule},${it.Mark}" }
    println("Writing data to file: $studentData")
    Files.write(file, studentData.split("\n"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
    println("Student added successfully.")
}
//Remove Student
fun removeStudent(StudentToBeRemoved: Student){
    val file = File("StudentDatabase.txt")
    val lines = file.readLines()
    var students = lines.map { line ->
        val parts = line.split(",")
        Student(parts[0].toInt(), parts[1], parts[2], parts[3].toInt(), parts[4], parts[5].toInt(), parts[6].toInt())
    }
    println("Students: $students")
    println("Student to remove: $StudentToBeRemoved")
    if (students.contains(StudentToBeRemoved)) {
        println("Found student to remove.")
        students = students.filterNot { it == StudentToBeRemoved }
    } else {
        println("Student to remove not found.")
    }
    val studentData = students.joinToString("\n") { "${it.ID},${it.firstName},${it.lastName},${it.age},${it.courseName},${it.courseModule},${it.Mark}" }
    file.writeText(studentData)
    println("Student removed successfully.")
}

fun getStudent(): List<Student> {
    students = recordStudent()
    return students
}
//talk about how originically was it... == it... but this was a direct match only and looked over the assignement brief again and found if it matches a given letter
//Get Functions
fun getID(ID: Int): List<Student> {
    return students.filter{ it.ID.toString().contains(ID.toString()) }
}
fun getfirstNames(firstName: String): List<Student> {
    return students.filter { it.firstName.lowercase().contains(firstName.lowercase()) }
}
fun getlastNames(lastName: String): List<Student> {
    return students.filter{ it.lastName.lowercase().contains(lastName.lowercase()) }
}
//converts the input to a string so that it can use the contains function to test both sides
fun getAge(age: Int): List<Student> {
    return students.filter{ it.age.toString().contains(age.toString()) }
}
fun getCourseName(courseName: String): List<Student>{
    return students.filter { it.courseName.lowercase().contains(courseName.lowercase()) }
}
fun getCourseModule(courseModule: Int): List<Student>{
    return students.filter{ it.courseModule.toString().contains(courseModule.toString()) }
}
fun getMark(Mark: Int): List<Student> {
    return students.filter{ it.Mark.toString().contains(Mark.toString()) }
}

//Combined Names //needs testing and debugging does take it both names but doesn't return it
//This takes in two inputs like first name and last name and then will find anything similar to the input
fun getCombinedNames(firstName: String, lastName: String): List<Student>{
    val NewFirstName = firstName.trim()
    val NewLastName = lastName.trim()
    //debugging
    println("Input first name: $NewFirstName")
    println("Input last name: $NewLastName")

    for (student in students) {
        println("Student first name: ${student.firstName}")
        println("Student last name: ${student.lastName}")
    }
    val CombinedStudents = students.filter{it.firstName.contains(NewFirstName) && it.lastName.contains(NewLastName)}
    println("Filtered ${CombinedStudents.size} students")
    return CombinedStudents;
}
fun getCourseNames(firstName: String, courseName: String): List<Student>{
    val NewFirstName = firstName.trim()
    val NewCourseName = courseName.trim()
    //debugging
    println("Input first name: $NewFirstName")
    println("Input last name: $NewCourseName")

    for (student in students) {
        println("Student first name: ${student.firstName}")
        println("Student last name: ${student.lastName}")
    }
    val CombinedStudents = students.filter{it.firstName.contains(NewFirstName) && it.courseName.contains(NewCourseName)}
    println("Filtered ${CombinedStudents.size} students")
    return CombinedStudents;
}


