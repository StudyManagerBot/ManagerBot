package app.discord.user.dto.attendance

import app.discord.user.dto.UserIdentifier
import java.time.OffsetDateTime

data class ServerMemberLeftEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,

    val channelId: String,
    val channelName: String,
    val leftTime: OffsetDateTime
)