package app.discord.user.repository

import app.discord.user.dto.UserRegisterEvent
import app.discord.user.entity.User

interface UserRepository {
    fun findAllUsers(): List<User>
    fun findUsersByUserId(userId: String): List<User>
    fun findUsersByGuildId(guildId: String): List<User>
    fun findUser(guildId: String, userId: String): User
    fun registerUser(user: UserRegisterEvent)
    fun updateUser(user: User)
}