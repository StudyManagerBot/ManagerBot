package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.*
import java.time.OffsetDateTime
import java.util.regex.Pattern

class User (
    val userIdentifier: UserIdentifier,
    userName: String = "",
    globalName: String,
    nickname: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    val isBan: Boolean,
    private val userAttendanceHistory: Map<UserIdentifier, UserAttendanceHistory>
){
    val userName: String = userName isNotInclude Pattern.compile(SQL_INJECTION_REGEX)
    val globalName: String = globalName isNotInclude Pattern.compile(SQL_INJECTION_REGEX)
    val nickname: String = nickname isNotInclude Pattern.compile(SQL_INJECTION_REGEX)

    private val attendance: Attendance = Attendance(histories = this.userAttendanceHistory)

    companion object {
        fun isNewUser(user: User?): Boolean {
            return user == null
        }
        private const val SQL_INJECTION_REGEX = "('.+--)|(--)|(%7C)|(;)|(\\b(SELECT|INSERT|UPDATE|DELETE|DROP|TRUNCATE|CREATE|ALTER|GRANT|REVOKE|UNION|ALL)\\b)"
    }

    fun updateUserInfo(userName: String = "",
                       globalName: String = "",
                       nickname: String = "",
                       leaveTime: OffsetDateTime = this.leaveTime): User
    {
        validateCheck(userName, errorMessage = "Invalid user name")
        validateCheck(globalName, errorMessage = "Invalid global name")
        validateCheck(nickname, errorMessage = "Invalid nickname")

        return User(
            userIdentifier = this.userIdentifier,
            userName = userName.ifBlank { this.userName },
            globalName = globalName.ifBlank { this.globalName },
            nickname = nickname.ifBlank { this.nickname },
            registerTime = this.registerTime,
            leaveTime = leaveTime,
            isBan = this.isBan,
            userAttendanceHistory = userAttendanceHistory,
        )
    }

    fun leaveUser(leaveTime: OffsetDateTime): User{
        return User(
            userIdentifier = this.userIdentifier,
            userName = this.userName,
            globalName = this.globalName,
            nickname = this.nickname,
            registerTime = this.registerTime,
            leaveTime = leaveTime,
            isBan = this.isBan,
            userAttendanceHistory = userAttendanceHistory,
        )
    }

    fun joinAttendance(event: ServerMemberJoinEvent): AttendanceResult =
        this.attendance.checkAttendance(serverMemberJoinEvent = event)

    fun leftAttendance(event: ServerMemberLeftEvent): AttendanceResult =
        this.attendance.checkAttendance(serverMemberLeftEvent = event)

    private fun validateCheck(field: String, errorMessage: String) {
        field.validate(
            { !Pattern.matches(SQL_INJECTION_REGEX, it) },
            errorMessage = errorMessage
        )
    }
    
    private fun String.validate(validator: (String) -> Boolean, errorMessage: String){
        if (!validator(this)) throw IllegalArgumentException("$userName $errorMessage")
    }

    private infix fun String.isNotInclude(invalidPattern: Pattern): String{
        if(invalidPattern.matcher(this).matches()) throw IllegalArgumentException("Invalid name detected")
        else return this
    }


}

