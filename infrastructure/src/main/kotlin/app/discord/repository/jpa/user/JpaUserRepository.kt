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
        else{throw NullPointerException("no have user")}
    }

    override fun insertUser(user: User): User{
        val userEntity = toJpaEntity(user = user)
        jpaUserEntityRepository.save(userEntity)

        val userEntityIdentifier = UserEntityIdentifier(
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId
        )
        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)

        return toDomainEntity(entity = userEntity, jpaAttendanceHistories = jpaAttendanceHistories)
    }


    override fun updateUser(user: User): User {
        val nowUser = jpaUserEntityRepository.findByGuildIdAndUserId(guildId = user.userIdentifier.guildId, userId = user.userIdentifier.userId)
            ?: throw IllegalArgumentException("no user")
        val userEntity = toJpaEntity(user = user, id = nowUser.id)
        jpaUserEntityRepository.save(userEntity)

        val userEntityIdentifier = UserEntityIdentifier(
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId
        )
        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)

        return toDomainEntity(entity = userEntity, jpaAttendanceHistories = jpaAttendanceHistories)

    }

    override fun deleteUser() {
        TODO("해당 유저 삭제.")
    }

    private fun toJpaEntity(user: User, id: Long = 0L)=
        UserEntity(
            id = id,
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId,
            username = user.userName,
            globalName = user.globalName,
            nickname = user.nickname,
            isBan = user.isBan,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )



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
            if (jpaAttendanceHistories.isNotEmpty()) UserAttendanceHistoryMapper.map(jpaAttendanceHistories = jpaAttendanceHistories) else mapOf()
        )
        return user
    }
}