package app.discord.user.dto

data class UserUpdateEvent(
    val guildId: Long,
    val userId: Long,
    val name: String,
    val nickname: String
)