package app.discord.user.dto

import java.time.OffsetDateTime

data class MemberRegisterEvent(
    val guildId: Long,
    val userId: Long,
    val username: String,
    val globalName: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    val activated: Boolean
)