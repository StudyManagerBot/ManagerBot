package app.discord.command

data class CommandRegisterOptions(
    val commandKeyword: String,
    val description: String,

    //Command length
    val minLength: Int = 0,
    val maxLength: Int = 30
)