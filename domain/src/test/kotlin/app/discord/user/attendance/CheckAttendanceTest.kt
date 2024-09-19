package app.discord.user.attendance

import app.discord.user.dto.attendance.AttendanceStatus
import app.discord.user.entity.Attendance
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Duration

class CheckAttendanceTest : BehaviorSpec({
    val userIdentifier = AttendanceDomainBuilder.testUserIdentifier()
    val serverMemberJoinEvent = ServerMemberEventBuilder.serverMemberJoinEvent(userIdentifier = userIdentifier)

    given("no attendance data"){
        val histories = AttendanceDomainBuilder.whenNoAttendanceData(userIdentifier = userIdentifier)
        val attendance = Attendance(histories = histories)

        `when`("do attendance"){
            val attendanceResult = attendance.updateAttendance(serverMemberJoinEvent = serverMemberJoinEvent)
            then("successfully works"){
                attendanceResult.status shouldBe AttendanceStatus.IN_PROGRESS
                attendanceResult.attendanceFailedReason shouldBe null
                attendanceResult.status.isOk() shouldBe true
                attendanceResult.totalAttendanceCount shouldBe 1
                attendanceResult.totalDuration shouldBe Duration.ZERO
                val timePeriod = attendanceResult.timePeriod
                timePeriod.startTime shouldBe serverMemberJoinEvent.joinTime
                timePeriod.endTime shouldBe null
                timePeriod.duration shouldBe Duration.ZERO
            }

        }
    }

    given("when attendance checked"){
        val histories = AttendanceDomainBuilder.whenAttendanceCheck(userIdentifier = userIdentifier)
        val attendance = Attendance(histories = histories)

        `when`("do attendance"){
            val duplicateAttendanceResult = attendance.updateAttendance(serverMemberJoinEvent = serverMemberJoinEvent)
            then("failed cause duplicate attendance detected"){
                duplicateAttendanceResult.status shouldBe AttendanceStatus.FAILURE
                duplicateAttendanceResult.attendanceFailedReason shouldNotBe null
                duplicateAttendanceResult.totalAttendanceCount shouldBe 1
                duplicateAttendanceResult.totalDuration shouldBe Duration.ZERO
                val timePeriod = duplicateAttendanceResult.timePeriod
                timePeriod.startTime shouldBe serverMemberJoinEvent.joinTime
                timePeriod.endTime shouldBe null
                timePeriod.duration shouldBe Duration.ZERO
            }
        }
    }
})