package app.discord.user.dto

data class NickNameChangedEvent(
    val userIdentifier: UserIdentifier,
    val nickname: String
)