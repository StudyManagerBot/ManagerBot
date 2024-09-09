package app.discord.user.entity

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.attendance.UserAttendanceHistory
import java.time.OffsetDateTime
import java.util.regex.Pattern

class User (
    val userIdentifier: UserIdentifier,
    val userName: String,
    val globalName: String,
    val nickname: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime,
    val isBan: Boolean,
    private val userAttendanceHistory: Map<UserIdentifier, UserAttendanceHistory>
){
    private val attendance: Attendance = Attendance(histories = this.userAttendanceHistory)
    private val isBasicNamePattern = Pattern.compile(BASIC_NAME_REGEX)

    companion object {
        fun isNewUser(user: User?): Boolean {
            return user == null
        }
        fun check(){

        }
        private const val BASIC_NAME_REGEX = "^[a-zA-Z0-9\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$"
        private const val ENGLISH_BAD_WORD_REGEX = "(fuck|shit|bitch|asshole|bastard|damn|crap|dick|piss|cunt|whore|slut)"
        private const val SQL_INJECTION_REGEX = "('.+--)|(--)|(%7C)|(;)|(\\b(SELECT|INSERT|UPDATE|DELETE|DROP|TRUNCATE|CREATE|ALTER|GRANT|REVOKE|UNION|ALL)\\b)"
    }

    fun updateUserInfo(userName: String = "",
                       globalName: String = "",
                       nickname: String = ""): User
    {

        validateCheck(userName, "user name")
        validateCheck(globalName, "global name")
        validateCheck(nickname, "nickname")

        return User(
            userIdentifier = this.userIdentifier,
            userName = userName.ifBlank { this.userName },
            globalName = globalName.ifBlank { this.globalName },
            nickname = nickname.ifBlank { this.nickname },
            registerTime = this.registerTime,
            leaveTime = this.leaveTime,
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

    private fun String.validate(validator: (String) -> Boolean, errorMessage: String){
        if (!validator(this)) throw IllegalArgumentException(errorMessage)
    }

    private fun validateCheck(field: String, fieldName: String) {
        field.validate(
            { Pattern.matches(BASIC_NAME_REGEX, it) &&
                    !Pattern.matches(ENGLISH_BAD_WORD_REGEX, it) &&
                    !Pattern.matches(SQL_INJECTION_REGEX, it) },
            errorMessage = "Invalid $fieldName"
        )
    }

    //TODO("attendance 진입점 추가")


}

