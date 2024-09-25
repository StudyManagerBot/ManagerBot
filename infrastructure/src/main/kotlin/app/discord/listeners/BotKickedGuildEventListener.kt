package app.discord.listeners

import app.discord.user.dto.BotKickedEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import org.springframework.context.ApplicationEventPublisher



@DiscordEventHandler
class BotKickedGuildEventListener (
    applicationEventPublisher: ApplicationEventPublisher
) :DiscordListener(applicationEventPublisher){
    override fun onGuildLeave(event: GuildLeaveEvent) {
        this.applicationEventPublisher.publishEvent(
            this.botKickedEvent(event = event)
        )
    }

    private fun botKickedEvent(event: GuildLeaveEvent) =
        BotKickedEvent(guildId = event.guild.id)
}