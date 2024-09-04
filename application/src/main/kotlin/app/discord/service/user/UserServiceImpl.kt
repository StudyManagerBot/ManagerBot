package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.dto.attendance.UserAttendanceHistory
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService{

    override fun registerUser(userRegisterEvent: UserRegisterEvent) {
        val user: User? = userRepository.findUser(userIdentifier = userRegisterEvent.userIdentifier)

        if(User.isNewUser(user)){
            val registerUser = this.toDomainEntity(
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
        val updateUser = this.toDomainEntity(
            userIdentifier = userUpdateEvent.userIdentifier,
            userName = userUpdateEvent.userName,
            globalName = user.globalName,
            nickname = userUpdateEvent.nickname,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )

        userRepository.updateUser(user = updateUser)
    }

    override fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = userNickNameChangedEvent.userIdentifier)
        val updateUser = this.toDomainEntity(
            userIdentifier = user.userIdentifier,
            userName = user.userName,
            globalName = user.globalName,
            nickname = userNickNameChangedEvent.nickname,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )
        userRepository.updateUser(user = updateUser)
    }

    override fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent) {
        val user: User = userRepository.findUserWithNullException(userIdentifier = guildMemberLeaveEvent.userIdentifier)
        val leavedUser = this.toDomainEntity(
            userIdentifier = user.userIdentifier,
            userName = user.userName,
            globalName = user.globalName,
            nickname = user.nickname,
            registerTime = user.registerTime,
            leaveTime = guildMemberLeaveEvent.leaveTime
        )
        userRepository.updateUser(user = leavedUser)
    }

    private fun toDomainEntity(
        userIdentifier: UserIdentifier,
        userName: String,
        globalName: String,
        nickname: String,
        registerTime: OffsetDateTime,
        leaveTime: OffsetDateTime,
        isBan: Boolean = false,
        userAttendanceHistory: Map<UserIdentifier, UserAttendanceHistory> = emptyMap()
    ) =
        User(
            userIdentifier = userIdentifier,
            userName = userName,
            globalName = globalName,
            nickname = nickname,
            registerTime = registerTime,
            leaveTime = leaveTime,
            isBan = isBan,
            userAttendanceHistory = userAttendanceHistory
        )

}