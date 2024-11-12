package app.discord.user.attendance

import app.discord.user.DEFAULT_CHANNEL_ID
import app.discord.user.DEFAULT_CHANNEL_NAME
import app.discord.user.DEFAULT_USER_NAME
import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.ChannelMemberJoinEvent
import app.discord.user.userRegisterEvent
import java.time.LocalDateTime

fun serverMemberJoinEvent(userIdentifier: UserIdentifier,
                          joinTime: LocalDateTime = LocalDateTime.now(),
                          userName: String = DEFAULT_USER_NAME) =
    ChannelMemberJoinEvent(
        userIdentifier = userIdentifier,
        channelId = DEFAULT_CHANNEL_ID,
        channelName = DEFAULT_CHANNEL_NAME,
        joinTime = joinTime,
        userName = userName,
        userRegisterEvent = userRegisterEvent(),
    )