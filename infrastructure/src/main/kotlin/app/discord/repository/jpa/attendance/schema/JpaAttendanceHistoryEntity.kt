package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import java.time.OffsetDateTime

@Entity
class JpaAttendanceHistoryEntity(
    @field:EmbeddedId
    val userIdentifier: UserIdentifier,

    @field:Column(name = "DATE")
    val date: OffsetDateTime,

    @field:Column(name = "ATTENDANCE_TIME")
    val attendanceTime: OffsetDateTime,

    @field:Column(name = "EXIT_TIME")
    val exitTime: OffsetDateTime?
)