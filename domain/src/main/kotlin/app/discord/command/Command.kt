package app.discord.command

import app.discord.command.response.CommandResponse

abstract class Command(

){
    abstract fun runCommand(): CommandResponse
    abstract fun parseCommandType(): CommandType
//    abstract fun parseCommand(): Command
}