package app.discord.configurations

import app.discord.jda.JDAInstance
import app.discord.listeners.DiscordEventHandler
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

class EventListenerAdapter(
    private val beanFactory: ConfigurableListableBeanFactory
) {

    fun addListeners(jdaInstance: JDAInstance) =
    this.beanFactory.getBeansWithAnnotation(DiscordEventHandler::class.java).values.forEach { jdaInstance.jda.addEventListener(it) }

}