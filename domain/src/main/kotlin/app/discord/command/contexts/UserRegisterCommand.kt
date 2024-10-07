package app.discord.command.contexts

import app.discord.command.Command
import app.discord.command.CommandRegisterOptions
import app.discord.command.CommandType
import app.discord.command.response.CommandResponse

class UserRegisterCommand: Command(
) {
    companion object{
        const val USER_REGISTER_COMMAND_KEYWORD = "init_users"
        const val USER_REGISTER_COMMAND_DESCRIPTION = "Initialize new users"
        val USER_REGISTER_COMMAND_OPTIONS = CommandRegisterOptions(
            commandKeyword = USER_REGISTER_COMMAND_KEYWORD,
            description = USER_REGISTER_COMMAND_DESCRIPTION
        )
    }
    override fun parseCommandType(): CommandType = CommandType.SIMPLE

    override fun runCommand(): CommandResponse {
        TODO("Not yet implemented")
    }
}