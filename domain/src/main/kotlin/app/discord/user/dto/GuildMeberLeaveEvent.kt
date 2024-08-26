package app.discord.user.dto

import java.time.OffsetDateTime

data class GuildMeberLeaveEvent(
    val guildId: String,
    val userId: String,
    val leaveTimestamp: OffsetDateTime
)