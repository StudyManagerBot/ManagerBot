package app.discord.user.dto

data class ChangeNickNameEvent(
    val guildId: String,
    val memberId: String,
    val nickname: String
)