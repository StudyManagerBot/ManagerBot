package app.discord.user.dto

data class GuildMemberLeaveEvent(
    val userIdentifier: UserIdentifier,
    val isLeft: Boolean,
)