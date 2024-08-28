package app.discord.attendance.entity

import app.discord.attendance.dto.UserAttendance

internal class AttendanceHistory(
    attendanceHistories: List<UserAttendance>
) {
    private var totalAttendanceCounts = attendanceHistories.size

    private fun countAttendanceCount(attendanceHistories: List<UserAttendance>){
        TODO()
    }
}