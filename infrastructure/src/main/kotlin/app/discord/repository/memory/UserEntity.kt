package app.discord.repository.memory

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.TimeZoneStorage
import org.hibernate.annotations.TimeZoneStorageType
import java.time.OffsetDateTime

@Entity(name = "Users")
class UserEntity(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.AUTO)
    val UsersId: Long,

    @field: Column(name = "GUILD_ID", nullable = false, unique = true)
    val guildId: String,

    @field: Column(name = "USER_ID", nullable = false, unique = true)
    val userId: String,

    @field: Column(name = "USER_NAME", nullable = false, length = 32)
    val username: String,

    @field: Column(name = "GLOBAL_NAME", nullable = true, length = 32)
    val globalName: String?,

    @field: Column(name = "NICKNAME", nullable = true, length = 32)
    val nickname: String,

    @field: CreationTimestamp
    @field: Column(name = "CREATED_TIME", nullable = false)
    val registerTime: OffsetDateTime,


    @field: Column(name = "LEAVE_TIME", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    val leaveTime: OffsetDateTime


)