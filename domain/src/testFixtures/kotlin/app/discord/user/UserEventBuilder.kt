package app.discord.user

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.attendance.ChannelMemberJoinEvent
import app.discord.user.dto.attendance.ChannelMemberLeftEvent
import java.time.LocalDateTime

const val DEFAULT_GUILD_ID = "testGuildId"
const val DEFAULT_USER_ID = "testUserId"
val DEFAULT_USER_IDENTIFIER = UserIdentifier(guildId = DEFAULT_GUILD_ID, userId = DEFAULT_USER_ID)

const val DEFAULT_USER_NAME = "testUserName"
const val DEFAULT_NICK_NAME = "testNickName"
const val DEFAULT_CHANNEL_ID = "testAudioChannelId"
const val DEFAULT_CHANNEL_NAME = "testAudioChannelName"

const val INVALID_SPECIAL_STRING = "'; DROP TABLE users; --"
const val INVALID_EMPTY_STRING= "   "

val DEFAULT_REGISTER_TIME: LocalDateTime = LocalDateTime.now()
const val DEFAULT_LEFT_STATUS: Boolean = false


fun userRegisterEvent(
    globalName: String = "",
    nickName: String = "",
    registerTime: LocalDateTime = DEFAULT_REGISTER_TIME,
    isLeft: Boolean = DEFAULT_LEFT_STATUS
) =
    UserRegisterEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        globalName = globalName,
        nickname = nickName,
        registerTime = registerTime,
        isLeft = isLeft,
    )
fun serverMemberJoinEvent() =
    ChannelMemberJoinEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        channelId = DEFAULT_CHANNEL_ID,
        channelName = DEFAULT_CHANNEL_NAME,
        joinTime = LocalDateTime.now(),
        userRegisterEvent = UserRegisterEvent(
            userIdentifier = DEFAULT_USER_IDENTIFIER,
            userName = DEFAULT_USER_NAME,
            globalName = "",
            nickname = "",
            registerTime = DEFAULT_REGISTER_TIME,
            isLeft = DEFAULT_LEFT_STATUS
    )
)

fun serverMemberLeftEvent() =
    ChannelMemberLeftEvent(
        userIdentifier = DEFAULT_USER_IDENTIFIER,
        userName = DEFAULT_USER_NAME,
        channelId = DEFAULT_CHANNEL_ID,
        channelName = DEFAULT_CHANNEL_NAME,
        leavedTime = LocalDateTime.now(),
    )