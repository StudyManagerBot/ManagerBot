package app.discord.user.dto.attendance

import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime

data class TimePeriod(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime?,
){
    val duration: Duration = if( endTime != null ) Duration.between(startTime, endTime) else Duration.ZERO

    fun checkEndTime(endTime: LocalDateTime) =
        TimePeriod(startTime = this.startTime, endTime = endTime)
}
