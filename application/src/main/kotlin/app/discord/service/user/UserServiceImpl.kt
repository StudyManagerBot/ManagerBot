package app.discord.service.user

import app.discord.user.dto.NickNameChangedEvent
import app.discord.user.entity.User
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import app.discord.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService{

    override fun registerUser(userRegisterEvent: UserRegisterEvent) {
        // 1. 유저가 존재하는지?
        // 2. 있다면 그 유저가 누구인지
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
            userRepository.insertUser(user = registerUser)
        }
        else TODO()
    }

    override fun updateUser(userUpdateEvent: UserUpdateEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userUpdateEvent.userIdentifier)
        val updateUser = User(
            userIdentifier = userUpdateEvent.userIdentifier,
            userName = userUpdateEvent.userName,
            globalName = user.globalName,
            nickname = userUpdateEvent.nickname,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime,
            isBan = false,
            userAttendanceHistory = emptyMap()
        )

        userRepository.updateUser(user = updateUser)
    }

    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userNickNameChangedEvent.userIdentifier)
        val updateUser = User(
            userIdentifier = userNickNameChangedEvent.userIdentifier,
            userName = user.userName,
            globalName = user.globalName,
            nickname = userNickNameChangedEvent.nickname,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime,
            isBan = false,
            userAttendanceHistory = emptyMap()
        )
        userRepository.updateUser(user = updateUser)
    }


    // 1. event의 추상클래스 작성
    // 2. ??

}