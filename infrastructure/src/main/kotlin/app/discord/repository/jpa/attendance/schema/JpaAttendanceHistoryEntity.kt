package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@Entity(name = "histories")
class JpaAttendanceHistoryEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @field:Embedded
    val userIdentifier: UserEntityIdentifier,

    @field:Column(name = "DATE")
    val date: LocalDate,

    @field:Column(name = "ATTENDANCE_TIME")
    val attendanceTime: LocalDateTime,

    @field:Column(name = "EXIT_TIME")
    val exitTime: LocalDateTime?
){
    private val tolerance = 1L
    override fun equals(other: Any?): Boolean
        = other is JpaAttendanceHistoryEntity &&
            id == other.id &&
            userIdentifier == other.userIdentifier &&
            date == other.date &&
//            this.attendanceTime == other.attendanceTime &&
//            this.exitTime == other.exitTime
            this.isEqualOffsetDateTime(attendanceTime, other.attendanceTime) &&
            this.areExitTimesEqual(exitTime = exitTime, other.exitTime)

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userIdentifier.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + attendanceTime.hashCode()
        result = 31 * result + (exitTime?.hashCode() ?: 0)
        return result
    }

    private fun areExitTimesEqual(exitTime: LocalDateTime?, other: LocalDateTime?): Boolean
    = when {
        exitTime == null && other == null -> true
        exitTime != null && other != null -> isEqualOffsetDateTime(exitTime, other)
        else -> false
    }

    private fun isEqualOffsetDateTime(target: LocalDateTime, other: LocalDateTime): Boolean{
        val timeDifference = ChronoUnit.SECONDS.between(target, other)
        return timeDifference.absoluteValue <= tolerance
    }
}