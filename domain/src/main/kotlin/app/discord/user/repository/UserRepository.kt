package app.discord.user.repository

import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User

interface UserRepository {
    fun findUser(userIdentifier: UserIdentifier): User?
    fun insertUser(user: User)
    fun updateUser(user: User)
}