package app.discord.attendance.entity

import app.discord.attendance.dto.ServerMemberJoinEvent
import app.discord.attendance.dto.ServerMemberLeftEvent
import app.discord.attendance.dto.UserAttendanceHistory
import java.time.OffsetDateTime

// will become subdomain of user entity
internal class Attendance(
    histories: UserAttendanceHistory
){
    private val attendanceHistories: Map<OffsetDateTime, AttendanceHistory> = this.classifyHistory(histories = histories)

    private fun classifyHistory(histories: UserAttendanceHistory)
    = histories.attendanceDates.associateBy({ it.date }, { AttendanceHistory(attendanceHistories = listOf(it)) })

    fun updateAttendance( serverMemberJoinEvent: ServerMemberJoinEvent){

    }

    fun updateAttendance( serverMemberLeftEvent: ServerMemberLeftEvent){

    }
}