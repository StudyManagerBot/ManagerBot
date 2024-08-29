package app.discord.user.entity

import app.discord.attendance.dto.UserAttendanceHistory
import app.discord.attendance.entity.Attendance
import java.time.OffsetDateTime

class User (
    val guildId: String,
    val userId: String,
    val username: String,
    val globalName: String,
    val nickName: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    val isBan: Boolean,
    userAttendanceHistory: UserAttendanceHistory
){
    private val attendance: Attendance
    init{
        attendance = Attendance(histories = userAttendanceHistory)
    }

}