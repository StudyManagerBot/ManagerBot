package app.discord.user.entity

import app.discord.user.dto.attendance.TimePeriod
import app.discord.user.dto.attendance.UserAttendanceHistory
import java.time.OffsetDateTime

internal class AttendanceHistory(
    attendanceHistories: UserAttendanceHistory
) {
    private val histories: MutableMap<OffsetDateTime, MutableList<TimePeriod>> = this.classifyWithDate(attendanceHistories = attendanceHistories)

    private fun classifyWithDate(attendanceHistories: UserAttendanceHistory): MutableMap<OffsetDateTime, MutableList<TimePeriod>>
    = attendanceHistories.attendanceDates.groupBy(
        { it.date },
        { TimePeriod(it.attendanceTime, it.exitTime) }
    ).mapValues { it.value.toMutableList() }.toMutableMap()

    internal fun doAttendance(attendanceTime: OffsetDateTime){
        TODO()
//        this.histories[attendanceTime] 에 추가하기. 
    }
}
