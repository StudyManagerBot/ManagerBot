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
        { it.date },
        { TimePeriod(it.attendanceTime, it.exitTime) }
    ).mapValues { map ->
        map.value.sortedBy { it.startTime }.toMutableList()
    }.toMutableMap()

    internal fun checkAttendance(attendanceTime: OffsetDateTime) =
        this.checkAttendanceStartTime(
            startTime = attendanceTime,
            timePeriods = this.histories.getOrPut(attendanceTime.toLocalDate()) { mutableListOf() }
        )

    internal fun checkExitTime(endTime: OffsetDateTime) : AttendanceResult{
        val timePeriods: MutableList<TimePeriod> = this.histories[endTime.toLocalDate()] ?:
        return this.toAttendanceResult(
            status = AttendanceStatus.FAILURE, date = endTime, startTime = endTime, endTime = endTime)
        val checkPoint: TimePeriod = timePeriods[timePeriods.lastIndex]
        timePeriods[timePeriods.lastIndex] = timePeriods[timePeriods.lastIndex].checkEndTime(endTime = endTime)
        return this.toAttendanceResult(
            status = AttendanceStatus.CHECKED, date = endTime,
            startTime = checkPoint.startTime, endTime = checkPoint.endTime,
            timePeriods = timePeriods
        )
    }


    private fun checkAttendanceStartTime(startTime: OffsetDateTime, timePeriods: MutableList<TimePeriod>): AttendanceResult{
        val lastPeriod = timePeriods.lastOrNull()
        if(lastPeriod != null && lastPeriod.endTime == null) {
            return this.toAttendanceResult(
                status = AttendanceStatus.FAILURE,
                date = startTime, startTime = startTime, timePeriods = timePeriods,
                attendanceFailedReason = AttendanceFailedReason(
                    detail = "Duplicate attendance detected. ${lastPeriod.startTime}"
                ))
//            throw RuntimeException("Double Check detected.")
        }
        timePeriods.add(TimePeriod(startTime = startTime, endTime = null))
        return AttendanceResult(
            status = AttendanceStatus.IN_PROGRESS,
            date = startTime.toLocalDate(),
            timePeriod = TimePeriod( startTime = startTime, endTime = null),
            totalAttendanceCount = timePeriods.size,
            totalDuration = this.getTodayTotalStudyTime(timePeriods = timePeriods)
        )
    }

    private fun getTodayTotalStudyTime(timePeriods: MutableList<TimePeriod>): Duration =
        timePeriods.fold(Duration.ZERO) { total, period -> total.plus(period.duration) }

    private fun toAttendanceResult(
        status: AttendanceStatus, date: OffsetDateTime, timePeriods: MutableList<TimePeriod> = mutableListOf(),
        startTime: OffsetDateTime, endTime: OffsetDateTime? = null, attendanceFailedReason: AttendanceFailedReason? = null
    ) =
        AttendanceResult(
            status = status,
            date = date.toLocalDate(),
            timePeriod = TimePeriod(startTime = startTime, endTime = endTime),
            totalAttendanceCount = timePeriods.size,
            totalDuration = this.getTodayTotalStudyTime(timePeriods = timePeriods),
            attendanceFailedReason = attendanceFailedReason
        )
}
