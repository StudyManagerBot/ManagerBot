package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.*
import java.time.OffsetDateTime

internal class Attendance(
    histories: Map<UserIdentifier, UserAttendanceHistory>
){
    private val attendanceHistories = histories.mapValues {
        AttendanceHistory(attendanceHistories = it.value)
    }.toMutableMap()

    internal fun checkAttendance( serverMemberJoinEvent: ServerMemberJoinEvent): AttendanceResult =
        this.attendanceHistories.getOrPut(serverMemberJoinEvent.userIdentifier) {
            AttendanceHistory(
                attendanceHistories = UserAttendanceHistory(
                    userIdentifier = serverMemberJoinEvent.userIdentifier,
                    attendanceDates = listOf(
//                        UserAttendance(
//                            attendanceTime = serverMemberJoinEvent.joinTime,
//                            date = serverMemberJoinEvent.joinTime.toLocalDate(),
//                            exitTime = null
//                        )
                    )
                )
            )
        }.checkAttendance(attendanceTime = serverMemberJoinEvent.joinTime)


    internal fun checkAttendance( serverMemberLeftEvent: ServerMemberLeftEvent ): AttendanceResult =
        attendanceHistories[serverMemberLeftEvent.userIdentifier]?.checkExitTime(serverMemberLeftEvent.leftTime)
            ?: throw IllegalArgumentException("user attendance history not exists")
}