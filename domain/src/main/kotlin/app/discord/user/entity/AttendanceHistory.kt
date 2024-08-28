package app.discord.user.entity

import app.discord.user.dto.attendance.UserAttendance

internal class AttendanceHistory(
    attendanceHistories: List<UserAttendance>
) {
    private var totalAttendanceCounts = attendanceHistories.size

    private fun countAttendanceCount(attendanceHistories: List<UserAttendance>){
        TODO()
    }
}