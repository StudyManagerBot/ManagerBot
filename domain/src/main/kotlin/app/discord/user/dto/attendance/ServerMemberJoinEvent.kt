package app.discord.user.dto.attendance

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import java.time.OffsetDateTime

data class ServerMemberJoinEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,

    val channelId: String,
    val channelName: String,
    val joinTime: OffsetDateTime,
    val userRegisterEvent: UserRegisterEvent
)