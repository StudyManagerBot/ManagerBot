package app.discord.listeners

import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.context.ApplicationEventPublisher

/**
 * Abstract base class for Discord event listeners.
 *
 * @param applicationEventPublisher The application event publisher to publish events.
 */
abstract class DiscordListener(
    val applicationEventPublisher: ApplicationEventPublisher
): ListenerAdapter() {

    internal fun publishEvent(event: Any) = this.applicationEventPublisher.publishEvent(event)
}