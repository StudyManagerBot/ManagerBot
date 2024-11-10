package app.discord.user

import app.discord.user.attendance.singleCheckAttendanceHistory
import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User

fun validUser()= User(
    userIdentifier = DEFAULT_USER_IDENTIFIER,
    userName = DEFAULT_USER_NAME,
    globalName = DEFAULT_USER_NAME,
    nickname = DEFAULT_NICK_NAME,
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
    globalName = DEFAULT_USER_NAME,
    nickname = DEFAULT_NICK_NAME,
    registerTime = DEFAULT_REGISTER_TIME,
    leaveTime = DEFAULT_MIN_TIME,
    isBan = false,
    userAttendanceHistory = emptyMap()
)

fun validUserWithHistory() = User(
    userIdentifier = DEFAULT_USER_IDENTIFIER,
    userName = DEFAULT_USER_NAME,
    globalName = DEFAULT_USER_NAME,
    nickname = DEFAULT_NICK_NAME,
    registerTime = DEFAULT_REGISTER_TIME,
    leaveTime = DEFAULT_MIN_TIME,
    isBan = false,
    userAttendanceHistory = singleCheckAttendanceHistory(
        userIdentifier = DEFAULT_USER_IDENTIFIER
    )
)