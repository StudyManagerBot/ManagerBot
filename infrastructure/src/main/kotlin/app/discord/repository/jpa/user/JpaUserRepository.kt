package app.discord.repository.jpa.user

import app.discord.repository.jpa.user.schema.UserEntity
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository

class JpaUserRepository(
    private val jpaUserEntityRepository: JpaUserEntityRepository
): UserRepository {
    override fun findAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun findUsersByUserId(userId: String): List<User> {
        TODO("Not yet implemented")
    }

    override fun findUsersByGuildId(guildId: String): List<User> {
        TODO("Not yet implemented")
    }

    override fun findUser(guildId: String, userId: String): User {
        TODO("Not yet implemented")

    }



    override fun registerUser(user: UserRegisterEvent) {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}