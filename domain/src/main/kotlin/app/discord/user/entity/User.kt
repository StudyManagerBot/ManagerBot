package app.discord.user.entity

import app.discord.attendance.dto.UserAttendanceHistory
import app.discord.attendance.entity.Attendance
import java.time.OffsetDateTime

class User (
    val usersId: Long,
    val guildId: Long,
    val userId: Long,
    val username: String,
    val globalName: String,
    val nickName: String,
    val registerTime: OffsetDateTime,
    val lastVisitTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    userAttendanceHistory: UserAttendanceHistory
){
    private val attendance: Attendance
    init{
        attendance = Attendance(histories = userAttendanceHistory)
    }

}