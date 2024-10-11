package app.discord.user.dto.attendance

import java.time.Duration
import java.time.OffsetDateTime

data class TimePeriod(
    val startTime: OffsetDateTime,
    val endTime: OffsetDateTime?,
){
    val duration = if( endTime != null ) Duration.between(startTime, endTime) else Duration.ZERO

    fun checkEndTime(endTime: OffsetDateTime) =
        TimePeriod(startTime = this.startTime, endTime = endTime)
}
