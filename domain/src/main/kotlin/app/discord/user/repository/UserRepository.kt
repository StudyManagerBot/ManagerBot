package app.discord.user.repository

import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User


interface UserRepository {
    fun findUser(userIdentifier: UserIdentifier): User?
    fun findUserWithNullException(userIdentifier: UserIdentifier): User
    fun insertUser(user: User): User
    fun updateUser(user: User): User
    fun deleteAllMembers(guildId: String)
}