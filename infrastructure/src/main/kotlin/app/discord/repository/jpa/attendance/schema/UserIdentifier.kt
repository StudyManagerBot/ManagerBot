package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class UserIdentifier(
    @field:Column(name = "GUILD_ID")
    val guildId: Long,

    @field:Column(name = "USER_ID")
    val userId: String
): Serializable