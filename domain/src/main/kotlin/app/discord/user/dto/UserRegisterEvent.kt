package app.discord.user.dto

import java.time.LocalDateTime

data class UserRegisterEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,
    val globalName: String,
    val nickname: String,
    val registerTime: LocalDateTime,
    val leaveTime: LocalDateTime,
)