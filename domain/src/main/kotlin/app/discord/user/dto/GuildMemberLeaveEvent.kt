package app.discord.user.dto

import java.time.LocalDateTime
import java.time.OffsetDateTime

data class GuildMemberLeaveEvent(
    val userIdentifier: UserIdentifier,
    val leaveTime: LocalDateTime
)