package app.discord.repository.jpa.role.schema

import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import jakarta.persistence.*

@Entity(name = "permission")
class Permission(
    @field: Id
    @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:Embedded
    @field:Column(name = "user_entity_identifier")
    val userEntityIdentifier: UserEntityIdentifier,

    
)