package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService{

    @Transactional
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
        else {
            val oldUser: User = userRepository.findUserWithNullException(userIdentifier = userRegisterEvent.userIdentifier)
            oldUser.updateUserInfo(
                userName = userRegisterEvent.userName,
                globalName = userRegisterEvent.globalName,
                nickname = userRegisterEvent.nickname,
                leaveTime = userRegisterEvent.leaveTime
            )
            userRepository.updateUser(user = oldUser)
            return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
        }
    }

    @Transactional
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

    @Transactional
    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent): UserResult {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userNickNameChangedEvent.userIdentifier)
        val nickNameChangedUser = user.updateUserInfo(nickname = userNickNameChangedEvent.nickname)

        userRepository.updateUser(user = nickNameChangedUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    @Transactional
    override fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent): UserResult {
        val user: User = userRepository.findUserWithNullException(userIdentifier = guildMemberLeaveEvent.userIdentifier)
        val leavedUser = user.leaveUser(leaveTime = guildMemberLeaveEvent.leaveTime)

        userRepository.updateUser(user = leavedUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    @Transactional
    override fun registerGuildMembers(registerGuildMembers: List<UserRegisterEvent>) {
        TODO("길드의 모든 멤버를 등록하여야함.")
    }

    @Transactional
    override fun deleteAllGuildMembers(botKickedEvent: BotKickedEvent) {
        userRepository.deleteAllMembers(guildId = botKickedEvent.guildId)
    }
}
