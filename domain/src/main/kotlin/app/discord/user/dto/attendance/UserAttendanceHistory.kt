package app.discord.user.dto.attendance

data class UserAttendanceHistory(
    val guildId: Long,
    val userId: String,
    val attendanceDates: List<UserAttendance>
)