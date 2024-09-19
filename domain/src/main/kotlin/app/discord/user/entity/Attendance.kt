package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.dto.attendance.ServerMemberLeftEvent
import app.discord.user.dto.attendance.UserAttendance
import app.discord.user.dto.attendance.UserAttendanceHistory
import java.time.OffsetDateTime

internal class Attendance(
    histories: Map<UserIdentifier, UserAttendanceHistory>
){
    private val attendanceHistories = histories.mapValues {
        AttendanceHistory(attendanceHistories = it.value)
    }.toMutableMap()

    internal fun updateAttendance( serverMemberJoinEvent: ServerMemberJoinEvent) =
        this.attendanceHistories.getOrPut(serverMemberJoinEvent.userIdentifier) {
            AttendanceHistory(
                attendanceHistories = UserAttendanceHistory(
                    userIdentifier = serverMemberJoinEvent.userIdentifier,
                    attendanceDates = listOf(
                        UserAttendance(
                            attendanceTime = serverMemberJoinEvent.joinTime,
                            date = serverMemberJoinEvent.joinTime.toLocalDate(),
                            exitTime = null
                        )
                    )
                )
            )
        }.checkAttendance(attendanceTime = serverMemberJoinEvent.joinTime)


    internal fun updateAttendance( serverMemberLeftEvent: ServerMemberLeftEvent ){

    }
}