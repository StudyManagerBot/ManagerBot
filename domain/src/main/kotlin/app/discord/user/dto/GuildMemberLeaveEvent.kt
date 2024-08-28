package app.discord.user.dto

import java.time.OffsetDateTime

data class GuildMemberLeaveEvent(
    val guildId: Long,
    val userId: Long,
    val leaveTime: OffsetDateTime
)