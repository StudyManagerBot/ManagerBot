package app.discord.user

import app.discord.repository.jpa.user.schema.UserEntity
import java.time.OffsetDateTime

class JpaUserEntityBuilder private constructor() {
    companion object {
        fun validUser(
            guildId: String = "testGuildId",
            userId: String =  "testUserId"
        ) = UserEntity(
            guildId = guildId,
            userId = userId,
            username = "testUsername",
            globalName = "testGlobalName",
            nickname = "",
            isBan = false,
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN,
        )

        fun updateUser(
            id: Long,
            guildId: String = "testGuildId",
            userId: String =  "testUserId",
            globalName: String = "testUpdateGlobalName",
            nickname: String = "testUpdateNickname",
        ) = UserEntity(
            id = id,
            guildId = guildId,
            userId = userId,
            username = "testUpdateUsername",
            globalName = globalName,
            nickname = nickname,
            isBan = false,
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN,
        )
    }

}