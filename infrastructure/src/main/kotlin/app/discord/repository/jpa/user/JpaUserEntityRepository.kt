package app.discord.repository.jpa.user

import app.discord.repository.jpa.user.schema.UserEntity
import jakarta.persistence.Entity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JpaUserEntityRepository: JpaRepository<UserEntity, UUID>{
    fun findByGuildIdAndUserId(userId: String, guildId: String): Optional<UserEntity>
}