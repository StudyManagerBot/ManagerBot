package app.discord.user.dto.attendance

import java.time.LocalDate
import java.time.OffsetDateTime

data class UserAttendance(
    val date: LocalDate,
    val attendanceTime: OffsetDateTime,
    val exitTime: OffsetDateTime?
)