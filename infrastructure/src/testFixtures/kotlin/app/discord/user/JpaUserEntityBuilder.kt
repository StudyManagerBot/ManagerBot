package app.discord.user

import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.repository.jpa.user.schema.UserEntity
import java.time.OffsetDateTime

const val DEFAULT_GUILD_ID = "testGuildId"
const val DEFAULT_USER_ID = "testUserId"
const val DEFAULT_USER_NAME = "testUsername"
const val DEFAULT_GLOBAL_NAME = "testGlobalName"
val DEFAULT_USER_ENTITY_IDENTIFIER = UserEntityIdentifier(guildId = DEFAULT_GUILD_ID, userId = DEFAULT_USER_ID)


class JpaUserEntityBuilder private constructor() {
    companion object {
        fun validUser(
            guildId: String = DEFAULT_GUILD_ID,
            userId: String =  DEFAULT_USER_ID
        ) = UserEntity(
            userIdentifier = UserEntityIdentifier(guildId = guildId, userId = userId),
            username = DEFAULT_USER_NAME,
            globalName = DEFAULT_GLOBAL_NAME,
            nickname = "",
            isBan = false,
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN,
        )


        fun leavedUser(
            userEntity: UserEntity,
            leaveTime: OffsetDateTime = OffsetDateTime.now()
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
    }
}

fun UserEntity.change(
    id: Long = this.id,
    userIdentifier: UserEntityIdentifier = this.userIdentifier,
    username: String = this.username,
    globalName: String = this.globalName,
    nickname: String = this.nickname,
    isBan: Boolean = this.isBan,
    registerTime: OffsetDateTime = this.registerTime,
    leaveTime: OffsetDateTime = this.leaveTime
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