package app.discord.repository.jpa.user

import app.discord.repository.jpa.user.schema.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaUserEntityRepository: JpaRepository<UserEntity, Long>{
    fun findAllByGuildId(guildId: String): List<UserEntity>
    fun findByGuildIdAndUserId(guildId:String, userId:String): UserEntity?
}