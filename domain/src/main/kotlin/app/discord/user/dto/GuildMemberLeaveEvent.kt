package app.discord.user.dto

import java.time.OffsetDateTime

data class GuildMemberLeaveEvent(
    val guildId: String,
    val userId: String,
    val leaveTime: OffsetDateTime
)