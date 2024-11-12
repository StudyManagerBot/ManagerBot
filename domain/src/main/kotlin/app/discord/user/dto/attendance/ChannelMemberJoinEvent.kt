package app.discord.user.dto.attendance

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import java.time.LocalDateTime

data class ChannelMemberJoinEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,

    val channelId: String,
    val channelName: String,
    val joinTime: LocalDateTime,
    val userRegisterEvent: UserRegisterEvent
)