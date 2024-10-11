package app.discord.user.attendance

import app.discord.user.DEFAULT_CHANNEL_ID
import app.discord.user.DEFAULT_CHANNEL_NAME
import app.discord.user.DEFAULT_USER_NAME
import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.userRegisterEvent
import java.time.OffsetDateTime

class ServerMemberEventBuilder private constructor(){
    companion object{

        fun serverMemberJoinEvent(userIdentifier: UserIdentifier,
                                  joinTime: OffsetDateTime = OffsetDateTime.now(),
                                  userName: String = DEFAULT_USER_NAME) =
            ServerMemberJoinEvent(
                userIdentifier = userIdentifier,
                channelId = DEFAULT_CHANNEL_ID,
                channelName = DEFAULT_CHANNEL_NAME,
                joinTime = joinTime,
                userName = userName,
                userRegisterEvent = userRegisterEvent(),
            )
    }
}