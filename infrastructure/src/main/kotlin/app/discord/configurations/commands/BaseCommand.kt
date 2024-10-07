package app.discord.configurations.commands

import net.dv8tion.jda.api.interactions.commands.build.OptionData

@Deprecated(message = "for removal")
abstract class BaseCommand(
    val command: String,
    val description: String,
    val optionData: OptionData
) {

}