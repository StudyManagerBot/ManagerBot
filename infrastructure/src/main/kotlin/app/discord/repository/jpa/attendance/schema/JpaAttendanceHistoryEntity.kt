package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.*
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@Entity(name = "histories")
class JpaAttendanceHistoryEntity(

    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:Embedded
    val userIdentifier: UserEntityIdentifier,

    @field:Column(name = "DATE")
    val date: LocalDate,

    @field:Column(name = "ATTENDANCE_TIME")
    val attendanceTime: OffsetDateTime,

    @field:Column(name = "EXIT_TIME")
    val exitTime: OffsetDateTime?
){
    private val tolerance = 1L
    override fun equals(other: Any?): Boolean
        = other is JpaAttendanceHistoryEntity &&
            id == other.id &&
            userIdentifier == other.userIdentifier &&
            date == other.date &&
            this.isEqualOffsetDateTime(attendanceTime, other.attendanceTime) &&
            this.isEqualOffsetDateTime(exitTime, other.exitTime)

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userIdentifier.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + attendanceTime.hashCode()
        result = 31 * result + (exitTime?.hashCode() ?: 0)
        return result
    }

    fun isEqualOffsetDateTime(target: OffsetDateTime?, other: OffsetDateTime?): Boolean{
        val timeDifference = ChronoUnit.SECONDS.between(target, other)
        return timeDifference.absoluteValue <= tolerance
    }
}