package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.dto.attendance.ChannelMemberJoinEvent
import app.discord.user.dto.attendance.ChannelMemberLeftEvent
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
                isLeft = userRegisterEvent.isLeft,
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
                isLeft = userRegisterEvent.isLeft
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
        val leavedUser = user.leaveUser(isLeft = guildMemberLeaveEvent.isLeft)

        userRepository.updateUser(user = leavedUser)
        return UserResult(status = UserResultStatus.SUCCESS, errorMessage = "")
    }

    @Transactional
    override fun deleteAllGuildMembers(botKickedEvent: BotKickedEvent) {
        userRepository.deleteAllMembers(guildId = botKickedEvent.guildId)
    }

    @Transactional
    override fun channelJoin(serverMemberJoinEvent: ChannelMemberJoinEvent) {
        val isUser:User? = userRepository.findUser(userIdentifier = serverMemberJoinEvent.userIdentifier)
        if(isUser == null){
            val userRegisterEvent: UserRegisterEvent = UserRegisterEvent(
                userIdentifier = serverMemberJoinEvent.userRegisterEvent.userIdentifier,
                userName = serverMemberJoinEvent.userRegisterEvent.userName,
                globalName = serverMemberJoinEvent.userRegisterEvent.globalName,
                nickname = serverMemberJoinEvent.userRegisterEvent.nickname,
                registerTime = serverMemberJoinEvent.userRegisterEvent.registerTime,
                isLeft = serverMemberJoinEvent.userRegisterEvent.isLeft
            )
            this.registerUser(userRegisterEvent = userRegisterEvent)
        }
        val user: User = userRepository.findUserWithNullException(userIdentifier = serverMemberJoinEvent.userIdentifier)
        user.joinAttendance(event = serverMemberJoinEvent)
        userRepository.insertUser(user = user)
    }

    @Transactional
    override fun channelExit(serverMemberLeftEvent: ChannelMemberLeftEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = serverMemberLeftEvent.userIdentifier)
        user.leftAttendance(serverMemberLeftEvent)
        this.userRepository.insertUser(user = user)
    }
}
