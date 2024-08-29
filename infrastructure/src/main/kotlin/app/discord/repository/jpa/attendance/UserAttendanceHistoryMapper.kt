package app.discord.repository.jpa.attendance

import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.UserAttendance
import app.discord.user.dto.attendance.UserAttendanceHistory

/**
 * Mapper class used to map a list of JpaAttendanceHistoryEntity objects to a UserAttendanceHistory object.
 */
class UserAttendanceHistoryMapper private constructor(){

    companion object {
        /**
         * Maps a list of JpaAttendanceHistoryEntity objects to a Map of UserIdentifier to UserAttendanceHistory.
         *
         * @param jpaAttendanceHistories The list of JpaAttendanceHistoryEntity objects to be mapped.
         * @return The map of UserIdentifier to UserAttendanceHistory.
         * @throws IllegalArgumentException if the input list is empty.
         */
        fun map(jpaAttendanceHistories: List<JpaAttendanceHistoryEntity>): Map<UserIdentifier, UserAttendanceHistory>
        = jpaAttendanceHistories
            .ifEmpty { throw IllegalArgumentException("List is empty") }
            .groupBy { UserIdentifier(guildId = it.userIdentifier.guildId, userId = it.userIdentifier.userId) }
            .mapValues { ( identifier, histories ) ->
                UserAttendanceHistory(
                    userIdentifier = identifier,
                    attendanceDates = histories.map {
                        UserAttendance(date = it.date, attendanceTime = it.attendanceTime, exitTime = it.exitTime)
                    }
                )
            }
    }
}