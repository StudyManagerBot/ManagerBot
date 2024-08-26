package app.discord.attendance.dto

data class UserAttendanceHistory(
    val userId: String,
    val attendanceDates: List<UserAttendance>
)