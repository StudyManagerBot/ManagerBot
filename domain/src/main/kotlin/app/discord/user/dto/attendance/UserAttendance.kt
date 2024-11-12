package app.discord.user.dto.attendance

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime

data class UserAttendance(
    val date: LocalDate,
    val attendanceTime: LocalDateTime,
    val exitTime: LocalDateTime?
)