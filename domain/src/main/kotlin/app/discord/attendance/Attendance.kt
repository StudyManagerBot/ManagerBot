package app.discord.attendance

import app.discord.attendance.dto.ServerMemberJoinEvent
import app.discord.attendance.dto.ServerMemberLeftEvent
import app.discord.attendance.dto.UserAttendanceHistory

class Attendance(
    private val histories: List<UserAttendanceHistory>
){

    fun updateAttendance( serverMemberJoinEvent: ServerMemberJoinEvent){

    }

    fun updateAttendance( serverMemberLeftEvent: ServerMemberLeftEvent){

    }
}