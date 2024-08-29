package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
class JpaAttendanceHistoryEntity(

    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:Embedded
    val userIdentifier: UserEntityIdentifier,

    @field:Column(name = "DATE")
    val date: OffsetDateTime,

    @field:Column(name = "ATTENDANCE_TIME")
    val attendanceTime: OffsetDateTime,

    @field:Column(name = "EXIT_TIME")
    val exitTime: OffsetDateTime?
)