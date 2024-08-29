package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.UserAttendanceHistory
import java.time.OffsetDateTime

class User (
    val userIdentifier: UserIdentifier,
    val username: String,
    val globalName: String,
    val nickName: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    val isBan: Boolean,
    userAttendanceHistory: Map<UserIdentifier, UserAttendanceHistory>
){
    private val attendance: Attendance = Attendance(histories = userAttendanceHistory)
}