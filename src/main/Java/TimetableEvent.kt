import java.time.LocalDateTime

data class TimetableEvent(
    val userName: String,
    val name: String,
    val time: LocalDateTime,

    // in minutes
    val duration: Int,
    val room: String
)