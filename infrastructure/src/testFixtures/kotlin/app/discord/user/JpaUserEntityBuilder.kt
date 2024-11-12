package app.discord.user

import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.repository.jpa.user.schema.UserEntity
import java.time.LocalDateTime

const val DEFAULT_GUILD_ID = "testGuildId"
const val DEFAULT_USER_ID = "testUserId"
const val DEFAULT_USER_NAME = "testUsername"
const val DEFAULT_GLOBAL_NAME = "testGlobalName"
val DEFAULT_USER_ENTITY_IDENTIFIER = UserEntityIdentifier(guildId = DEFAULT_GUILD_ID, userId = DEFAULT_USER_ID)
val DEFAULT_REGISTER_TIME: LocalDateTime = LocalDateTime.now()
val DEFAULT_MIN_TIME: LocalDateTime = LocalDateTime.MIN

fun validUserEntity(
    guildId: String = DEFAULT_GUILD_ID,
    userId: String =  DEFAULT_USER_ID
) = UserEntity(
    userIdentifier = UserEntityIdentifier(guildId = guildId, userId = userId),
    username = DEFAULT_USER_NAME,
    globalName = DEFAULT_GLOBAL_NAME,
    nickname = "",
    isBan = false,
    registerTime = DEFAULT_REGISTER_TIME,
    leaveTime = DEFAULT_MIN_TIME
)

fun leavedUser(
    userEntity: UserEntity,
    leaveTime: LocalDateTime = LocalDateTime.now()
)= UserEntity(
    id = userEntity.id,
    userIdentifier = userEntity.userIdentifier,
    username = userEntity.username,
    globalName = userEntity.globalName,
    nickname = userEntity.nickname,
    isBan = userEntity.isBan,
    registerTime = userEntity.registerTime,
    leaveTime = leaveTime
)

fun UserEntity.change(
    id: Long = this.id,
    userIdentifier: UserEntityIdentifier = this.userIdentifier,
    username: String = this.username,
    globalName: String = this.globalName,
    nickname: String = this.nickname,
    isBan: Boolean = this.isBan,
    registerTime: LocalDateTime = this.registerTime,
    leaveTime: LocalDateTime = this.leaveTime
) = UserEntity(
    id = id,
    userIdentifier = userIdentifier,
    username = username,
    globalName = globalName,
    nickname = nickname,
    isBan = isBan,
    registerTime = registerTime,
    leaveTime = leaveTime
)