package app.discord.user.attendance

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import java.time.OffsetDateTime

class ServerMemberEventBuilder private constructor(){
    companion object{

        fun serverMemberJoinEvent(userIdentifier: UserIdentifier,
                                  joinTime: OffsetDateTime = OffsetDateTime.now(),
                                  userName: String = "testUserName") =
            ServerMemberJoinEvent(
                userIdentifier = userIdentifier,
                channelId = "testChannelId",
                channelName = "testChannelName",
                joinTime = joinTime,
                userName = userName
            )
    }
}