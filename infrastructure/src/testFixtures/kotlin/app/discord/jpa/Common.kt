package app.discord.jpa

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

const val TOLERANCE = 1L
private fun isEqualOffsetDateTime(exitTime: LocalDateTime, other: LocalDateTime): Boolean{
    val timeDifference = ChronoUnit.SECONDS.between(exitTime, other)
    return timeDifference.absoluteValue <= TOLERANCE
}

infix fun LocalDateTime?.isSame(localDateTime: LocalDateTime?): Boolean =
    when {
        this == null && localDateTime == null -> true
        this != null && localDateTime != null -> isEqualOffsetDateTime(exitTime = this, other = localDateTime)
        else -> false
    }
