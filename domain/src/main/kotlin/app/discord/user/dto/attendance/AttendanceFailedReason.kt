package app.discord.user.dto.attendance

data class AttendanceFailedReason(
    val detail: String,
    val exception: Exception = RuntimeException(detail)
)