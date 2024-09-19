package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.*
import java.time.LocalDate
import java.time.OffsetDateTime

@Entity
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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JpaAttendanceHistoryEntity) return false

        if (id != other.id) return false
        if (userIdentifier != other.userIdentifier) return false
        if (date != other.date) return false
        if (attendanceTime != other.attendanceTime) return false
        if (exitTime != other.exitTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userIdentifier.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + attendanceTime.hashCode()
        result = 31 * result + (exitTime?.hashCode() ?: 0)
        return result
    }
}