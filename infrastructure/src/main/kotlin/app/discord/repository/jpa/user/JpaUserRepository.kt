package app.discord.repository.jpa.user

import app.discord.repository.jpa.attendance.JpaAttendanceHistoryRepository
import app.discord.repository.jpa.attendance.UserAttendanceHistoryMapper
import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.repository.jpa.user.schema.UserEntity
import app.discord.user.dto.UserIdentifier
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository

class JpaUserRepository(
    private val jpaUserEntityRepository: JpaUserEntityRepository,
    private val jpaAttendanceHistoryRepository: JpaAttendanceHistoryRepository
): UserRepository {

    override fun findUser(userIdentifier: UserIdentifier): User {
        val userEntity:UserEntity = jpaUserEntityRepository.findByGuildIdAndUserId(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val userEntityIdentifier = UserEntityIdentifier(
                guildId = userIdentifier.guildId,
                userId = userIdentifier.userId
            )

        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)

        return this.toDomainEntity(userEntity, jpaAttendanceHistories)
    }

    override fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    private fun toDomainEntity(entity: UserEntity, jpaAttendanceHistories: List<JpaAttendanceHistoryEntity>): User {
        val userIdentifier = UserIdentifier(guildId = entity.guildId, userId = entity.userId)
        val user = User(
            userIdentifier = userIdentifier,
            username = entity.username,
            globalName = entity.globalName,
            nickName = entity.nickname,
            registerTime = entity.registerTime,
            leaveTime = entity.leaveTime,
            isBan = entity.isBan,
            userAttendanceHistory =
            UserAttendanceHistoryMapper.map(jpaAttendanceHistories = jpaAttendanceHistories)
        )
        return user
    }
}