package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService{

    override fun registerUser(userRegisterEvent: UserRegisterEvent) {
        val user: User? = userRepository.findUser(userIdentifier = userRegisterEvent.userIdentifier)

        if(User.isNewUser(user)){
            val registerUser = User(
                userIdentifier = userRegisterEvent.userIdentifier,
                userName = userRegisterEvent.userName,
                globalName = userRegisterEvent.globalName,
                nickname = userRegisterEvent.nickname,
                registerTime = userRegisterEvent.registerTime,
                leaveTime = userRegisterEvent.leaveTime,
                isBan = false,
                userAttendanceHistory = emptyMap()
            )

            userRepository.insertUser(registerUser)
        }
        else TODO()
    }

    override fun updateUserInfo(userUpdateEvent: UserUpdateEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userUpdateEvent.userIdentifier)
        val updateUser = user.updateUserInfo(
            userName = userUpdateEvent.userName,
            globalName = user.globalName,
            nickname = userUpdateEvent.nickname
        )

        userRepository.updateUser(user = updateUser)
    }

    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userNickNameChangedEvent.userIdentifier)
        val nickNameChangedUser = user.updateUserInfo(nickname = userNickNameChangedEvent.nickname)

        userRepository.updateUser(user = nickNameChangedUser)
    }

    override fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = guildMemberLeaveEvent.userIdentifier)
        val leavedUser = user.leaveUser(leaveTime = guildMemberLeaveEvent.leaveTime)

        userRepository.updateUser(user = leavedUser)
    }
}
