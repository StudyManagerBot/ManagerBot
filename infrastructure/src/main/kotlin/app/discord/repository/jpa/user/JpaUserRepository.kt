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

    override fun findUser(userIdentifier: UserIdentifier): User? {
        val userEntity:UserEntity? = jpaUserEntityRepository.findByGuildIdAndUserId(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val userEntityIdentifier = UserEntityIdentifier(
                guildId = userIdentifier.guildId,
                userId = userIdentifier.userId
            )

        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)
        return if(userEntity != null) this.toDomainEntity(userEntity, jpaAttendanceHistories) else null
    }

    override fun findUserWithNullException(userIdentifier: UserIdentifier): User {
        val userEntity:UserEntity? = jpaUserEntityRepository.findByGuildIdAndUserId(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val userEntityIdentifier = UserEntityIdentifier(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)

        if(userEntity != null) {
            return this.toDomainEntity(userEntity, jpaAttendanceHistories)
        }
        else{throw NullPointerException("")} //FIXME
    }

    override fun insertUser(user: User) {
        val userEntity = UserEntity(
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId,
            username = user.userName,
            globalName = user.globalName,
            nickname = user.nickname,
            isBan = user.isBan,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )
        jpaUserEntityRepository.save(userEntity)
    }

    override fun updateUser(user: User) {
        //1 조회
        val userEntity = UserEntity(
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId,
            username = user.userName,
            globalName = user.globalName,
            nickname = user.nickname,
            isBan = user.isBan,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )
        jpaUserEntityRepository.save(userEntity)
    }

    private fun toDomainEntity(entity: UserEntity, jpaAttendanceHistories: List<JpaAttendanceHistoryEntity>): User {
        val userIdentifier = UserIdentifier(guildId = entity.guildId, userId = entity.userId)
        val user = User(
            userIdentifier = userIdentifier,
            userName = entity.username,
            globalName = entity.globalName,
            nickname = entity.nickname,
            registerTime = entity.registerTime,
            leaveTime = entity.leaveTime,
            isBan = entity.isBan,
            userAttendanceHistory =
            UserAttendanceHistoryMapper.map(jpaAttendanceHistories = jpaAttendanceHistories)
        )
        return user
    }
}