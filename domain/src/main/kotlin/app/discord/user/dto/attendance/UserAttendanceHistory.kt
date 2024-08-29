package app.discord.user.dto.attendance

import app.discord.user.dto.UserIdentifier

data class UserAttendanceHistory(
    val userIdentifier: UserIdentifier,
    val attendanceDates: List<UserAttendance>
)