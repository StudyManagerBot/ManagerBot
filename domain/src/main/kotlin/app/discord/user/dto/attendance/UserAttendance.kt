package app.discord.user.dto.attendance

import java.time.OffsetDateTime

data class UserAttendance(
    val date: OffsetDateTime,
    val attendanceTime: OffsetDateTime,
    val exitTime: OffsetDateTime?
)