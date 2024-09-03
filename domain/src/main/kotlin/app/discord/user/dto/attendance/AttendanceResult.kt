package app.discord.user.dto.attendance

import java.time.Duration
import java.time.OffsetDateTime

data class AttendanceResult(
    val status: AttendanceStatus,
    val date: OffsetDateTime,
    val timePeriod: TimePeriod,
    //Today's total attendance counts
    val totalAttendanceCount: Int,
    val totalDuration: Duration,
    //IsFailed
    val attendanceFailedReason: AttendanceFailedReason? = null
)