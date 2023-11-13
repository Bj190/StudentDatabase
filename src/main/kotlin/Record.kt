import java.nio.file.Files
import java.nio.file.Paths


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use


fun record() {
    data class Student(val firstName: String, val  lastName: String, val Age: Int, val courseName: String, val courseModule: String){}
    val file = Paths.get("StudentDatabase.txt")
    var studentlines = Files.readAllLines(file)
    studentlines
        .forEach {println(it)}



    data class Student(val firstName: String, val  lastName: String, val Age: Int, val courseName: String, val courseModule: String){}
    //val file = Paths.get("StudentDatabase.txt")
    //var studentlines = Files.readAllLines(file)
    //studentlines
        //.toString()
        //.forEach {println(it)}
}