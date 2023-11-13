import java.nio.file.Files
import java.nio.file.Paths
import kotlin.jvm.JvmRecord as JvmRecord1


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use

data class Student(val firstName: String, val  lastName: String, val Age: Int, val courseName: String, val courseModule: Int)
fun record() {
    //@JvmRecord1
    val file = Paths.get("StudentDatabase.txt")
    var studentlines = Files.readAllLines(file)
    "$studentlines"
        .trim(' ')
        .split(",")
        .forEach {println(it)}
        //sperate by comma
        //trim


}

