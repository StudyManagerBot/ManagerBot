package app.discord.user

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import java.time.OffsetDateTime

class UserEventBuilder private constructor() {
    companion object {
        const val INVALID_SPECIAL_STRING= "(*#%^!(*$&@!)@@$"
        const val INVALID_EMPTY_STRING= "   "
        fun userRegisterEvent(
            userIdentifier: UserIdentifier,
            userName: String = "testUserName",
            globalName: String = "",
            nickName: String = "",
            registerTime: OffsetDateTime = OffsetDateTime.now(),
            leaveTime: OffsetDateTime = OffsetDateTime.MIN
            ) =
            UserRegisterEvent(
                userIdentifier = userIdentifier,
                userName = userName,
                globalName = globalName,
                nickname = nickName,
                registerTime = registerTime,
                leaveTime = leaveTime,
        )

    }
}