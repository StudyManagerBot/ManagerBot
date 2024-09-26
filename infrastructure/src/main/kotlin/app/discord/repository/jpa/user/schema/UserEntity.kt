package app.discord.repository.jpa.user.schema

import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.user.entity.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime

@Entity(name = "Users")
class UserEntity(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @field: Embedded
    val userIdentifier: UserEntityIdentifier,

    @field: Column(name = "USER_NAME", nullable = false)
    val username: String,

    @field: Column(name = "GLOBAL_NAME", nullable = false)
    val globalName: String,

    @field: Column(name = "NICKNAME", nullable = false)
    val nickname: String,

    @field: Column(name = "IS_BAN", nullable = false)
    val isBan: Boolean,

    @field: CreationTimestamp
    @field: Column(name = "CREATED_TIME")
    val registerTime: OffsetDateTime,

    @field: Column(name = "LEAVE_TIME")
    val leaveTime: OffsetDateTime,

    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "PERMISSION")
    val permission: UserRole,

    @field:Column(name = "WARNINGS")
    val warnings: Int = 0
)