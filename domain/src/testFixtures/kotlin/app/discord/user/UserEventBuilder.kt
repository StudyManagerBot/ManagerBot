package app.discord.user

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.dto.attendance.ServerMemberLeftEvent
import java.time.OffsetDateTime


const val DEFAULT_GUILD_ID = "testGuildId"
const val DEFAULT_USER_ID = "testUserId"
val DEFAULT_USER_IDENTIFIER = UserIdentifier(guildId = DEFAULT_GUILD_ID, userId = DEFAULT_USER_ID)

const val DEFAULT_USER_NAME = "testUserName"
const val DEFAULT_CHANNEL_ID = "testAudioChannelId"
const val DEFAULT_CHANNEL_NAME = "testAudioChannelName"

const val INVALID_SPECIAL_STRING = "'; DROP TABLE users; --"
const val INVALID_EMPTY_STRING= "   "

fun userRegisterEvent(
    globalName: String = "",
    nickName: String = "",
    registerTime: OffsetDateTime = OffsetDateTime.now(),
    leaveTime: OffsetDateTime = OffsetDateTime.MIN
) =
    UserRegisterEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        globalName = globalName,
        nickname = nickName,
        registerTime = registerTime,
        leaveTime = leaveTime,
    )
fun serverMemberJoinEvent() =
    ServerMemberJoinEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        channelId = DEFAULT_CHANNEL_ID,
        channelName = DEFAULT_CHANNEL_NAME,
        joinTime = OffsetDateTime.now(),
        userRegisterEvent = UserRegisterEvent(
            userIdentifier = DEFAULT_USER_IDENTIFIER,
            userName = DEFAULT_USER_NAME,
            globalName = "",
            nickname = "",
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN
    )
)

fun serverMemberLeftEvent() =
    ServerMemberLeftEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        channelId = DEFAULT_CHANNEL_ID,
        channelName = DEFAULT_CHANNEL_NAME,
        leftTime = OffsetDateTime.now(),
    )