import java.nio.file.Files
import java.nio.file.Paths


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use
data class Student(val firstName: String, val  lastName: String, val age: Int, val courseName: String, val courseModule: Int)
fun recordStudent(): List<Student>{

    //@JvmRecord1
    val file = Paths.get("StudentDatabase.txt")
    val studentLines = Files.readAllLines(file)
    return studentLines.map {line ->
        val tokens = line.replace(" ", "").split(",") //replace line.trim() with line.replace (" ", "") //question if works
        Student(firstName = tokens[0], lastName = tokens[1], age = tokens[2].toInt(), courseName = tokens[3], courseModule = tokens[4].toInt())
    }//map to list of Student as an array "need to know proper syntax?"
}
var students: List<Student> = emptyList()
fun getStudent(): List<Student> {
    students = recordStudent()
    return students
}
fun getfirstNames(firstName: String): List<Student> {
    return students.filter {it.firstName == firstName }
}
fun getlastNames(lastName: String): List<Student> {
    return students.filter{it.lastName == lastName}
}


