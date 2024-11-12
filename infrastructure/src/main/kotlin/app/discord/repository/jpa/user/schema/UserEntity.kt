package app.discord.repository.jpa.user.schema

import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

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

    @field: Column(name = "IS_LEAVED")
    val isLeft: Boolean = false,

    @field: CreationTimestamp
    @field: Column(name = "CREATED_TIME")
    val registerTime: LocalDateTime,

    @field: UpdateTimestamp
    @field: Column(name = "UPDATE_AT")
    val updateAt: LocalDateTime,

    )