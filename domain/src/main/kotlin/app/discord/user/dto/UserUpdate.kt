package app.discord.user.dto

data class UserUpdate(
    val guildId: String,
    val userId: String,
    val name: String,
    val nickname: String
)