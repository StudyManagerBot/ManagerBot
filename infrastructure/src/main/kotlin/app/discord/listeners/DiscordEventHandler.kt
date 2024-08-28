package app.discord.listeners

import app.discord.configurations.EnableDiscordBot
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Component

/**
 * Marks a class as a Discord event handler.
 *
 * This annotation should be used on classes that handle different types of Discord events.
 * The class must implement the necessary event handling methods for the specific events it is meant to handle.
 *
 * An event handler class should be marked with this annotation and registered with a Discord bot instance using an `EventListenerAdapter`.
 *
 * Example usage:
 *
 * ```
 * @DiscordEventHandler
 * class MyEventHandler(
 *     applicationEventPublisher: ApplicationEventPublisher
 * ) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {
 *
 *     override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
 *        */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ConditionalOnBean( annotation = [EnableDiscordBot::class] )
@Component
annotation class DiscordEventHandler
