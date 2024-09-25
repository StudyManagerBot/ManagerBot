package app.discord.user.dto.attendance

enum class AttendanceStatus {
    COMPLETED,
    CHECKED,
    FAILURE,
    UNKNOWN,
    IN_PROGRESS;

    fun isOk() = this == CHECKED || this == IN_PROGRESS || this == COMPLETED
}