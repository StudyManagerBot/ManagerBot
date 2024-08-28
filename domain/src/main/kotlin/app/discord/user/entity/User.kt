package app.discord.user.entity

import java.time.OffsetDateTime

class User (
    val guildId: String,
    val userId: String,
    val username: String,
    val globalName: String,
    val authId: Int,
    val ban: Boolean,
    val firstJoinTimestamp: OffsetDateTime
)