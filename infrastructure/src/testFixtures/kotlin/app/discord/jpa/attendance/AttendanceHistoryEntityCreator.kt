package app.discord.jpa.attendance

import app.discord.repository.jpa.attendance.schema.JpaAttendanceHistoryEntity
import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.user.DEFAULT_GUILD_ID
import app.discord.user.DEFAULT_USER_ID
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*


const val ATTENDANCE_HISTORY_COUNT = 10

fun createAttendanceHistoryEntity(id: Long = 1L, guildId: String = DEFAULT_GUILD_ID,
                                  userId: String = DEFAULT_USER_ID) = JpaAttendanceHistoryEntity(
    id = id,
    date = LocalDateTime.now().toLocalDate(),
    attendanceTime = OffsetDateTime.now(),
    exitTime = OffsetDateTime.now().plusHours(1),
    userIdentifier = UserEntityIdentifier(
        guildId = guildId,
        userId = userId
    )
)

fun createAttendanceHistoryEntityWithoutExitTime(id: Long = 1L, guildId: String = DEFAULT_GUILD_ID,
                                                 userId: String = DEFAULT_USER_ID)
= JpaAttendanceHistoryEntity(
    id = id,
    date = LocalDateTime.now().toLocalDate(),
    attendanceTime = OffsetDateTime.now(),
    exitTime = null,
    userIdentifier = UserEntityIdentifier(
        guildId = guildId,
        userId = userId
    )
)

fun attendanceHistories(startId: Long = 1L, isNullExit: Boolean = false)
= if( isNullExit ) (startId until startId + ATTENDANCE_HISTORY_COUNT).map { id ->
    createAttendanceHistoryEntity(id = id, guildId = UUID.randomUUID().toString(), userId = UUID.randomUUID().toString()) }
else (startId until startId + ATTENDANCE_HISTORY_COUNT).map {
    id -> createAttendanceHistoryEntityWithoutExitTime(id = id, guildId = UUID.randomUUID().toString(), userId = UUID.randomUUID().toString()) }

fun JpaAttendanceHistoryEntity.change(
                            id: Long? = null,
                            userIdentifier: UserEntityIdentifier? = null,
                            date: LocalDate? = null,
                            attendanceTime: OffsetDateTime? = null,
                            exitTime: OffsetDateTime? = null)
    = JpaAttendanceHistoryEntity(
        id = id ?: this.id,
        userIdentifier = userIdentifier ?: this.userIdentifier,
        date = date ?: this.date,
        attendanceTime = attendanceTime ?: this.attendanceTime,
        exitTime = exitTime ?: this.exitTime
    )

infix fun Collection<JpaAttendanceHistoryEntity>.isSameAll(others: Collection<JpaAttendanceHistoryEntity>): Boolean
= this.sortedBy { it.id }.zip(others.sortedBy { it.id }).all { it.first == it.second }
