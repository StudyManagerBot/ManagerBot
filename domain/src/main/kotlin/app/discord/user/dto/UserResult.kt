package app.discord.user.dto

data class UserResult (
    val status: UserResultStatus,
    val errorMessage: String
)