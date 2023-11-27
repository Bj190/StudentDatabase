
import java.time.LocalDateTime
import java.time.Month

class TimetableModel {

    private val events = arrayListOf<TimetableEvent>()

    init {
        events += TimetableEvent(
            userName = "aa",
            name = "Module 1",
            time = LocalDateTime.of(2023, Month.NOVEMBER, 20, 11, 0, 0),
            duration = 120,
            room = "Watts 301"
        )

        events += TimetableEvent(
            userName = "aa",
            name = "Module 2",
            time = LocalDateTime.of(2023, Month.NOVEMBER, 20, 16, 0, 0),
            duration = 60,
            room = "Watts 311"
        )

        events += TimetableEvent(
            userName = "bb",
            name = "Module 2",
            time = LocalDateTime.of(2023, Month.NOVEMBER, 20, 16, 0, 0),
            duration = 60,
            room = "Watts 311"
        )

        events += TimetableEvent(
            userName = "bb",
            name = "Module 3",
            time = LocalDateTime.of(2023, Month.NOVEMBER, 22, 11, 0, 0),
            duration = 180,
            room = "Elm House 405"
        )
    }

    fun getEventsInRoom(roomName: String): List<TimetableEvent> {
        return events.filter { it.room == roomName }
    }

    fun getEventsAtTime(time: LocalDateTime): List<TimetableEvent> {
        return events.filter { it.time == time }
    }

    fun getEventsForUser(userName: String): List<TimetableEvent> {
        return events.filter { it.userName == userName }
    }
}

fun main() {
    val model = TimetableModel()

    model.getEventsForUser("aa")
        .forEach { println(it) }
}