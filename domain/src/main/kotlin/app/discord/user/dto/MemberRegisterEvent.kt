package app.discord.user.dto

import java.time.OffsetDateTime

data class MemberRegisterEvent(
    val guildId: String,
    val userId: String,
    val username: String,
    val globalName: String,
    val registerTime: OffsetDateTime
)