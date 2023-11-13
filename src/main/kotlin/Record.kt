import java.nio.file.Files
import java.nio.file.Paths
import kotlin.jvm.JvmRecord as JvmRecord1


//need to inisliase record then it codes data properly with group by so it's sperated into String etc then it can be used for inputs and scanners
// ins -> data/groupby -> instructions to use

data class Student(val firstName: String, val  lastName: String, val age: Int, val courseName: String, val courseModule: Int)
fun record() {

    //@JvmRecord1
    val file = Paths.get("StudentDatabase.txt")
    val studentlines = Files.readAllLines(file)
    "$studentlines"
        .trim(' ')
        .split(",")
        .map {Student(" "," ",0," ",0)}//map to list of Student as an array "need to know proper syntax?"
        .forEach {println(it)}
        //sperate by comma
        //trim


}

