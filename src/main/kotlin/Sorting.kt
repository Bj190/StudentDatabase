import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun sort() {
    val scanner = Scanner(System.`in`)
    var queryIsOn = true

    val file = Paths.get("StudentDatabase.txt")
    var studentlines = Files.readAllLines(file)
    studentlines
        .forEach {println(it)}


    // week 5 hang man example insipration
    while(queryIsOn) {
        val inputSort = scanner.nextLine()
            .ifEmpty { "Empty" }
        println("-----------")
        println("You inputted$inputSort")
        if (studentlines.contains(inputSort)){
            studentlines.toString()
                .forEachIndexed { index, c ->
                    if (c.toString() == inputSort) {
                        println()
                    }
                }
        }



    }



}