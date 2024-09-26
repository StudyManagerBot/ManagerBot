package app.discord.repository.jpa.attendance

import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaAttendanceHistoryRepository : JpaRepository<JpaAttendanceHistoryEntity, Long>{
    fun findAllByUserIdentifier(userEntityIdentifier: UserEntityIdentifier): List<JpaAttendanceHistoryEntity>

}