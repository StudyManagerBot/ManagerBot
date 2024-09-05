package app.discord.user

import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User
import java.time.OffsetDateTime
import java.time.ZoneOffset

class UserDomainBuilder private constructor() {
    companion object{
        fun validUser(
            userIdentifier: UserIdentifier =
                UserIdentifier(
                    guildId = "testGuildId",
                    userId = "testUserId")
        )= User(
                    userIdentifier = userIdentifier,
                    userName = "testUserName",
                    globalName = "",
                    nickname = "",
                    registerTime = OffsetDateTime.now(),
                    leaveTime = OffsetDateTime.MIN,
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
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN,
            isBan = false,
            userAttendanceHistory = emptyMap()
        )


    }
}