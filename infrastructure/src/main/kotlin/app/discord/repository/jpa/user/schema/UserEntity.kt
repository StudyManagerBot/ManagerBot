package app.discord.repository.jpa.user.schema

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.TimeZoneStorage
import org.hibernate.annotations.TimeZoneStorageType
import java.time.OffsetDateTime

@Entity(name = "Users")
class UserEntity(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field: Column(name = "GUILD_ID")
    val guildId: String,

    @field: Column(name = "USER_ID")
    val userId: String,

    @field: Column(name = "USER_NAME")
    val username: String,

    @field: Column(name = "GLOBAL_NAME")
    val globalName: String,

    @field: Column(name = "NICKNAME")
    val nickname: String,

    @field: Column(name = "IS_BAN")
    val isBan: Boolean,

    @field: CreationTimestamp
    @field: TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    @field: Column(name = "CREATED_TIME")
    val registerTime: OffsetDateTime,

    @field: Column(name = "LEAVE_TIME")
    @field: TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
    val leaveTime: OffsetDateTime
)