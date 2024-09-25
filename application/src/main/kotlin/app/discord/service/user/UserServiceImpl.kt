package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService{

    override fun registerUser(userRegisterEvent: UserRegisterEvent): UserResult {
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
            return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
        }
        else TODO("기존 유저인 경우 create 시간을 업데이트, leave time을 min으로 수정하여야함.")
    }

    override fun updateUserInfo(userUpdateEvent: UserUpdateEvent): UserResult {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userUpdateEvent.userIdentifier)
        val updateUser = user.updateUserInfo(
            userName = userUpdateEvent.userName,
            globalName = user.globalName,
            nickname = userUpdateEvent.nickname
        )

        userRepository.updateUser(user = updateUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent): UserResult {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userNickNameChangedEvent.userIdentifier)
        val nickNameChangedUser = user.updateUserInfo(nickname = userNickNameChangedEvent.nickname)

        userRepository.updateUser(user = nickNameChangedUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    override fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent): UserResult {
        val user: User = userRepository.findUserWithNullException(userIdentifier = guildMemberLeaveEvent.userIdentifier)
        val leavedUser = user.leaveUser(leaveTime = guildMemberLeaveEvent.leaveTime)

        userRepository.updateUser(user = leavedUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    override fun registerGuildMembers(registerGuildMembers: List<UserRegisterEvent>) {
        TODO("모든 길드 멤버들을 등록하여야함.")
    }

    override fun deleteAllGuildMembers(botKickedEvent: BotKickedEvent) {
        TODO("해당 길드 id 유저 정보를 모두 삭제.")

    }
}
