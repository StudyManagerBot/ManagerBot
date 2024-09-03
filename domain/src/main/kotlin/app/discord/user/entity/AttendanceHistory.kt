package app.discord.user.entity

import app.discord.user.dto.attendance.*
import java.time.Duration
import java.time.LocalDate
import java.time.OffsetDateTime

internal class AttendanceHistory(
    attendanceHistories: UserAttendanceHistory
) {
    private val histories: MutableMap<LocalDate, MutableList<TimePeriod>> = this.classifyByDate(attendanceHistories = attendanceHistories)

    private fun classifyByDate(attendanceHistories: UserAttendanceHistory): MutableMap<LocalDate, MutableList<TimePeriod>>
    = attendanceHistories.attendanceDates.groupBy(
        { it.date.toLocalDate() },
        { TimePeriod(it.attendanceTime, it.exitTime) }
    ).mapValues { map ->
        map.value.sortedBy { it.startTime }.toMutableList()
    }.toMutableMap()

    internal fun checkAttendance(attendanceTime: OffsetDateTime) =
        this.checkAttendanceStartTime(
            startTime = attendanceTime,
            timePeriods = this.histories.getOrPut(attendanceTime.toLocalDate()) { mutableListOf() }
        )


    private fun checkAttendanceStartTime(startTime: OffsetDateTime, timePeriods: MutableList<TimePeriod>): AttendanceResult{
        val lastPeriod = timePeriods.lastOrNull()
        println(this.histories)
        if(lastPeriod != null && lastPeriod.endTime == null) {
            return AttendanceResult(
                status = AttendanceStatus.FAILURE,
                date = startTime,
                timePeriod = TimePeriod(startTime = startTime, endTime = null),
                totalAttendanceCount = timePeriods.size,
                totalDuration = this.getTodayTotalStudyTime(timePeriods = timePeriods),
                attendanceFailedReason = AttendanceFailedReason(
                    detail = "Duplicate attendance detected. ${lastPeriod.startTime}"
                )
            )
//            throw RuntimeException("Double Check detected.")
        }
        timePeriods.add(TimePeriod(startTime = startTime, endTime = null))
        return AttendanceResult(
            status = AttendanceStatus.IN_PROGRESS,
            date = startTime,
            timePeriod = TimePeriod( startTime = startTime, endTime = null),
            totalAttendanceCount = timePeriods.size,
            totalDuration = this.getTodayTotalStudyTime(timePeriods = timePeriods)
        )
    }

    private fun getTodayTotalStudyTime(timePeriods: MutableList<TimePeriod>): Duration =
        timePeriods.fold(Duration.ZERO) { total, period -> total.plus(period.duration) }




}
