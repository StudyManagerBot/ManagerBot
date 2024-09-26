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
        val userEntityIdentifier = UserEntityIdentifier(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val userEntity:UserEntity? =
            jpaUserEntityRepository.findByUserIdentifier(userIdentifier = userEntityIdentifier)

        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)
        return if(userEntity != null) this.toDomainEntity(userEntity, jpaAttendanceHistories) else null
    }


    override fun findUserWithNullException(userIdentifier: UserIdentifier): User {

        val userEntityIdentifier = UserEntityIdentifier(
            guildId = userIdentifier.guildId,
            userId = userIdentifier.userId
        )

        val userEntity:UserEntity? =
            jpaUserEntityRepository.findByUserIdentifier(userIdentifier = userEntityIdentifier)


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

        return toDomainEntity(jpaEntity = userEntity, jpaAttendanceHistories = jpaAttendanceHistories)
    }


    override fun updateUser(user: User): User {
        val nowUser =
            jpaUserEntityRepository.findByUserIdentifier(
                userIdentifier = UserEntityIdentifier(
                    guildId = user.userIdentifier.guildId,
                    userId = user.userIdentifier.userId
                )
            ) ?: throw IllegalArgumentException("no user")
        val userEntity = toJpaEntity(user = user, id = nowUser.id)
        jpaUserEntityRepository.save(userEntity)

        val userEntityIdentifier = UserEntityIdentifier(
            guildId = user.userIdentifier.guildId,
            userId = user.userIdentifier.userId
        )
        val jpaAttendanceHistories =
            jpaAttendanceHistoryRepository.findAllByUserIdentifier(userEntityIdentifier = userEntityIdentifier)

        return toDomainEntity(jpaEntity = userEntity, jpaAttendanceHistories = jpaAttendanceHistories)

    }

    override fun deleteAllMembers(guildId: String) {
        val members = jpaUserEntityRepository.findAllByUserIdentifierGuildId(guildId = guildId)
        jpaUserEntityRepository.deleteAllInBatch(members)
    }


    private fun toJpaEntity(user: User, id: Long = 0L)=
        UserEntity(
            id = id,
            userIdentifier = UserEntityIdentifier(
                guildId = user.userIdentifier.guildId,
                userId = user.userIdentifier.userId
            ),
            username = user.userName,
            globalName = user.globalName,
            nickname = user.nickname,
            isBan = user.isBan,
            permission = user.role,
            warnings = user.warnings,
            registerTime = user.registerTime,
            leaveTime = user.leaveTime
        )


    private fun toDomainEntity(jpaEntity: UserEntity, jpaAttendanceHistories: List<JpaAttendanceHistoryEntity>): User =
        User(
            userIdentifier = UserIdentifier(
                guildId = jpaEntity.userIdentifier.guildId,
                userId = jpaEntity.userIdentifier.userId,
            ),
            userName = jpaEntity.username,
            globalName = jpaEntity.globalName,
            nickname = jpaEntity.nickname,
            registerTime = jpaEntity.registerTime,
            leaveTime = jpaEntity.leaveTime,
            isBan = jpaEntity.isBan,
            role = jpaEntity.permission,
            warnings = jpaEntity.warnings,
            userAttendanceHistory =
            if (jpaAttendanceHistories.isNotEmpty()) UserAttendanceHistoryMapper.map(jpaAttendanceHistories = jpaAttendanceHistories) else mapOf()
        )

}