package app.discord.user.repository

import app.discord.user.entity.User

interface UserRepository {
    fun findUserByUserId(userId: Long): List<User>
    fun findUsersByGuildId(guildId: Long): List<User>
    fun registerUser(user: User)
    fun updateUser(user: User)

}