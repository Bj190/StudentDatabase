import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun sort() {
    val scanner = Scanner(System.`in`)
    var queryIsOn = true
    var output = ""

    val file = Paths.get("StudentDatabase.txt")
    var studentlines = Files.readAllLines(file)
    studentlines
        .forEach {println(it)}


    // week 5 hang man example insipration
    //while(queryIsOn) {
        //val inputSort = scanner.nextLine()
            //.ifEmpty { "Empty" }
        //println("-----------")
        //println("You inputted $inputSort")
        //if (studentlines.contains(inputSort)){
            //studentlines.toString()
                //.forEachIndexed { _, c ->
                    //if (c.toString() == inputSort) {
                        //output = output.mapIndexed {index 2, c2 ->
                        //if (index2 == index){
                            //c
                        //} else {
                            //c2
                        //}
                        //}.toString()
                    //}
                //}
        //}



    }



}