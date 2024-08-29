package app.discord.user.dto

import java.time.OffsetDateTime

data class UserRegisterEvent(
    val userIdentifier: UserIdentifier,
    val username: String,
    val globalName: String,
    val registerTime: OffsetDateTime
)
