package app.discord.repository.jpa.attendance

import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.user.dto.attendance.UserAttendance
import app.discord.user.dto.attendance.UserAttendanceHistory

class UserAttendanceHistoryMapper private constructor(){

    companion object {
        fun map(jpaAttendanceHistories: List<JpaAttendanceHistoryEntity>)
        = jpaAttendanceHistories
            .ifEmpty { throw IllegalArgumentException("List is empty") }
            .let {
                UserAttendanceHistory(
                    guildId = jpaAttendanceHistories[0].userIdentifier.guildId,
                    userId = jpaAttendanceHistories[0].userIdentifier.userId,
                    jpaAttendanceHistories.map {
                        UserAttendance(date = it.date, attendanceTime = it.attendanceTime, exitTime = it.exitTime)
                    }
                )
            }
    }
}