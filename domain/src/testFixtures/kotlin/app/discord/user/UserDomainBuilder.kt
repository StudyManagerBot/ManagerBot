package app.discord.user

import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User
class UserDomainBuilder private constructor() {
    companion object{
        fun validUser()= User(
                    userIdentifier = DEFAULT_USER_IDENTIFIER,
                    userName = DEFAULT_USER_NAME,
                    globalName = "",
                    nickname = "",
                    registerTime = DEFAULT_REGISTER_TIME,
                    leaveTime = DEFAULT_MIN_TIME,
                    isBan = false,
                    userAttendanceHistory = emptyMap()
                )

        fun inValidUserName(
            userIdentifier: UserIdentifier =
                UserIdentifier(
                    guildId = "testGuildId",
                    userId = "testUserId")
        )= User(
            userIdentifier = userIdentifier,
            userName = "*&%*\$(@!\$*(*!@^%)",
            globalName = "",
            nickname = "",
            registerTime = DEFAULT_REGISTER_TIME,
            leaveTime = DEFAULT_MIN_TIME,
            isBan = false,
            userAttendanceHistory = emptyMap()
        )


    }
}