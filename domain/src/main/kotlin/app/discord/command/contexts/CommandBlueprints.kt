package app.discord.command.contexts

import app.discord.command.CommandRegisterOptions
import app.discord.command.contexts.UserRegisterCommand.Companion.USER_REGISTER_COMMAND_OPTIONS

enum class CommandBlueprints(
    val options: CommandRegisterOptions
) {
    USER_REGISTER_COMMAND(USER_REGISTER_COMMAND_OPTIONS)
}