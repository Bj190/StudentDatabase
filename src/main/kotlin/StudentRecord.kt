import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

// @Author Bryn Jones 2023
// Version 1.0


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use
data class Student(val ID: Int, val firstName: String, val  lastName: String, val age: Int, val courseName: String, val courseModule: Int)
fun recordStudent(): MutableList<Student>{
    //This will read the file then if the data is stored correctly will read it and then convert into a record for the software to manipulate
    //@JvmRecord1
    val file = Paths.get("StudentDatabase.txt")
    val studentLines = Files.readAllLines(file)
    return studentLines.map {line ->
        val tokens = line.replace(" ", "").split(",") //replace line.trim() with line.replace (" ", "") //question if works
        Student(ID = tokens[0].toInt(), firstName = tokens[1], lastName = tokens[2], age = tokens[3].toInt(), courseName = tokens[4], courseModule = tokens[5].toInt())
    }.toMutableList(); //this was orignically a list which you can't add to.
}

var students: List<Student> = emptyList()
//Add student needs to take in the same info as a Student data class have the current record open and then write that into the file.
fun addStudent(newStudent: Student) {
    val students = recordStudent()
    students.add(newStudent)
    val file = Paths.get("StudentDatabase.txt")
    val studentData = students.joinToString("\n") { "${it.firstName},${it.lastName},${it.age},${it.courseName},${it.courseModule}" }
    Files.write(file, studentData.split("\n"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
}


fun getStudent(): List<Student> {
    students = recordStudent()
    return students
}
//talk about how originically was it... == it... but this was a direct match only and looked over the assignement brief again and found if it matches a given letter
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
fun getCombinedNames(firstName: String, lastName: String): List<Student>{
    println("Input first name: $firstName")
    println("Input last name: $lastName")

    for (student in students) {
        println("Student first name: ${student.firstName}")
        println("Student last name: ${student.lastName}")
    }
    return students.filter{it.firstName.lowercase().replaceFirstChar {firstName.uppercase()} == firstName && it.lastName.lowercase().replaceFirstChar {lastName.uppercase()} == lastName}; //bipedicate
}


