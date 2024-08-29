package app.discord.service.user

import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent


interface UserService {

    fun registerUser(userRegisterEvent: UserRegisterEvent)
    fun updateUser(userUpdateEvent: UserUpdateEvent)
}