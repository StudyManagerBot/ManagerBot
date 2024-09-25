package app.discord.configurations

import app.discord.configurations.commands.BaseCommand
import app.discord.configurations.commands.EnableSlashCommand
import app.discord.jda.JDAInstance
import app.discord.listeners.DiscordEventHandler
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

class EventListenerAdapter(
    private val beanFactory: ConfigurableListableBeanFactory
) {

    fun addListeners(jdaInstance: JDAInstance) =
    this.beanFactory.getBeansWithAnnotation(DiscordEventHandler::class.java).values.forEach { jdaInstance.jda.addEventListener(it) }

    fun addSlashCommands(jdaInstance: JDAInstance) =
        jdaInstance.jda.updateCommands().addCommands(
            this.beanFactory.getBeansWithAnnotation(EnableSlashCommand::class.java).values
                .filterIsInstance<BaseCommand>()
                .map { Commands.slash(it.command, it.description) }
        )
    
}