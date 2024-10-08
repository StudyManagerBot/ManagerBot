package app.discord.listeners

import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.context.ApplicationEventPublisher

abstract class DiscordListener(
    val applicationEventPublisher: ApplicationEventPublisher
): ListenerAdapter() {

}