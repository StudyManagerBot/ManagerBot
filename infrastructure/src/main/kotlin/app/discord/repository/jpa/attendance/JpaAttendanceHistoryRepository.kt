package app.discord.repository.jpa.attendance

import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface JpaAttendanceHistoryRepository : JpaRepository<JpaAttendanceHistoryEntity, Long>{
    fun findAllByUserIdentifier(userEntityIdentifier: UserEntityIdentifier): List<JpaAttendanceHistoryEntity>

    @Query("SELECT h FROM histories h ORDER BY h.attendanceTime DESC")
    fun findFirstOrderByAttendanceTimeDesc(): JpaAttendanceHistoryEntity?
}