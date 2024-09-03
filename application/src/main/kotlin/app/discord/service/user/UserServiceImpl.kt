package app.discord.service.user

import app.discord.user.dto.NickNameChangedEvent
import app.discord.user.entity.User
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import app.discord.user.repository.UserRepository

class UserServiceImpl(
    private val repository: UserRepository
) : UserService{

    override fun registerUser(event: UserRegisterEvent) {
        val user: User? = repository.findUser(userIdentifier = event.userIdentifier)
        if(User.isNewUser(user)){
            user?.let { repository.insertUser(user = it) }
        }

    }

    override fun updateUser(userUpdateEvent: UserUpdateEvent) {
        TODO("Not yet implemented")
    }

    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent) {
        TODO("Not yet implemented")
    }


}