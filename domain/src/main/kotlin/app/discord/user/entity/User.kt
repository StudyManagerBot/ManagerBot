package app.discord.user.entity

class User (
    val id: String,
    val username: String,
    val globalName: String,
    val authId: Int,
    val enabled: Boolean,
    val firstJoinTimestamp: Long,
    val guildOutTimestamp: Long,
    val lastActivityTimestamp: Long
){
}
