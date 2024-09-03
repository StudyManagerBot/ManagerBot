package app.discord.user.dto

import java.time.OffsetDateTime

class UserRegisterEvent(
    userIdentifier: UserIdentifier,
    userName: String,
    globalName: String,
    nickname: String,
    registerTime: OffsetDateTime,
    leaveTime: OffsetDateTime,
): UserEvent(
    userIdentifier = userIdentifier,
    userName = userName,
    globalName = globalName,
    nickname = nickname,
    registerTime = registerTime,
    leaveTime = leaveTime
)
