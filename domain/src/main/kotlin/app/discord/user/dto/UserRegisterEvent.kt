package app.discord.user.dto

import java.time.OffsetDateTime

data class UserRegisterEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,
    val globalName: String,
    val nickname: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
)