package app.discord.user.dto

data class ChangedNickNameEvent(
    val guildId: String,
    val memberId: String,
    val nickname: String
)