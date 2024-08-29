package app.discord.user.dto

data class UserUpdateEvent(
    val userIdentifier: UserIdentifier,
    val name: String,
    val nickname: String
)