package app.discord.repository.jpa.attendance.schema

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class UserEntityIdentifier(
    @field:Column(name = "GUILD_ID")
    val guildId: String,

    @field:Column(name = "USER_ID")
    val userId: String
): Serializable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntityIdentifier

        if (guildId != other.guildId) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int = 31 * guildId.hashCode() + userId.hashCode()
}