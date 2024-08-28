package app.discord.user.dto.attendance

import java.time.OffsetDateTime

data class ServerMemberJoinEvent(
    val userId: String,
    val userName: String,

    val channelId: String,
    val channelName: String,
    val joinTime: OffsetDateTime
)