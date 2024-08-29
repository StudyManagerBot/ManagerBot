package app.discord.service.user

import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import app.discord.user.repository.UserRepository

class UserServiceImpl(
    private val repository: UserRepository
) : UserService{
    override fun registerUser(userRegisterEvent: UserRegisterEvent) {
        repository.registerUser(userRegisterEvent)
    }

    override fun updateUser(userUpdateEvent: UserUpdateEvent) {

    }

}