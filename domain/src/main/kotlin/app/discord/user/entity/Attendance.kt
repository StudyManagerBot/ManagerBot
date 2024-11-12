package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.*

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

    internal fun getUserTotalAttendanceHistories(userIdentifier: UserIdentifier? = null): List<UserAttendanceHistory> {
        return when (userIdentifier) {
            null -> this.attendanceHistories.map { (identifier, attendanceHistory) ->
                UserAttendanceHistory(
                    userIdentifier = identifier,
                    attendanceDates = attendanceHistory.getAllAttendanceHistories()
                )
            }
            else -> {
                this.attendanceHistories[userIdentifier]?.let {
                    listOf(UserAttendanceHistory(
                        userIdentifier = userIdentifier,
                        attendanceDates = it.getAllAttendanceHistories()
                    ))
                } ?: emptyList()
            }
        }
    }

    internal fun getLatestAttendanceHistory(userIdentifier: UserIdentifier): UserAttendance? =
        this.attendanceHistories[userIdentifier]?.getLatestHistory()
}