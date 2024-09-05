package app.discord.user.dto

data class UserUpdateEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,
    val nickname: String
)