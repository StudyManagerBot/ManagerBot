package app.discord.repository.jpa.user.schema

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime

@Entity(name = "Users")
class UserEntity(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @field: Column(name = "GUILD_ID", nullable = false)
    var guildId: String,

    @field: Column(name = "USER_ID", nullable = false)
    var userId: String,

    @field: Column(name = "USER_NAME", nullable = false)
    var username: String,

    @field: Column(name = "GLOBAL_NAME", nullable = false)
    var globalName: String,

    @field: Column(name = "NICKNAME", nullable = false)
    var nickname: String,

    @field: Column(name = "IS_BAN", nullable = false)
    var isBan: Boolean,

    @field: CreationTimestamp
    @field: Column(name = "CREATED_TIME")
    var registerTime: OffsetDateTime,

    @field: Column(name = "LEAVE_TIME")
    var leaveTime: OffsetDateTime

    //TODO Setter 막을것.
)