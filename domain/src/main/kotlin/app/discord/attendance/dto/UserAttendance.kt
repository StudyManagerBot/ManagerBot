package app.discord.attendance.dto

import java.time.Instant
import java.time.OffsetDateTime

data class UserAttendance(
    val userId: String,
    val date: Instant,
    val attendanceTime: OffsetDateTime,
    val exitTime: OffsetDateTime
)