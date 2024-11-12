package app.discord.user.dto.attendance

import app.discord.user.dto.UserIdentifier
import java.time.LocalDateTime

data class ChannelMemberLeftEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,

    val channelId: String,
    val channelName: String,
    val leavedTime: LocalDateTime,
)