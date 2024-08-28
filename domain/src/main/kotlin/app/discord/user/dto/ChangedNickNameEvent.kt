package app.discord.user.dto

data class ChangedNickNameEvent(
    val guildId: Long,
    val memberId: Long,
    val nickname: String
)