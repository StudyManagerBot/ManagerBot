package app.discord.jpa

import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

const val TOLERANCE = 1L
private fun isEqualOffsetDateTime(exitTime: OffsetDateTime, other: OffsetDateTime): Boolean{
    val timeDifference = ChronoUnit.SECONDS.between(exitTime, other)
    return timeDifference.absoluteValue <= TOLERANCE
}

infix fun OffsetDateTime?.isSame(otherOffsetDateTime: OffsetDateTime?): Boolean =
    when {
        this == null && otherOffsetDateTime == null -> true
        this != null && otherOffsetDateTime != null -> isEqualOffsetDateTime(exitTime = this, other = otherOffsetDateTime)
        else -> false
    }
