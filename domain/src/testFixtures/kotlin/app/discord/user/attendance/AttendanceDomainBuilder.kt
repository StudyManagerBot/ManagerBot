package app.discord.user.attendance

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.UserAttendance
import app.discord.user.dto.attendance.UserAttendanceHistory
import java.time.OffsetDateTime

class AttendanceDomainBuilder private constructor() {

    companion object{
        fun whenAttendanceCheck(userIdentifier: UserIdentifier = UserIdentifier(
            guildId = "testGuildId",
            userId = "testUserId")
        ): Map<UserIdentifier, UserAttendanceHistory>{
            val now = OffsetDateTime.now()
            val attendanceDates: List<UserAttendance> = listOf(
                UserAttendance(
                    date = now,
                    attendanceTime = now,
                    exitTime = null
                )
            )
            return mapOf(
                Pair(
                    userIdentifier,
                    UserAttendanceHistory(
                    userIdentifier = userIdentifier,
                    attendanceDates = attendanceDates
                    )
                )
            )
        }

        fun whenNoAttendanceData(userIdentifier: UserIdentifier = UserIdentifier(
            guildId = "testGuildId",
            userId = "testUserId")
        ): Map<UserIdentifier, UserAttendanceHistory>{
            val attendanceDates: List<UserAttendance> = listOf()
            return mapOf(
                Pair(
                    userIdentifier,
                    UserAttendanceHistory(
                        userIdentifier = userIdentifier,
                        attendanceDates = attendanceDates
                    )
                )
            )
        }

        fun testUserIdentifier(guildId: String = "testGuildId",
                               userId: String = "testUserId"): UserIdentifier
        = UserIdentifier(
            guildId = guildId,
            userId = userId
        )
    }
}