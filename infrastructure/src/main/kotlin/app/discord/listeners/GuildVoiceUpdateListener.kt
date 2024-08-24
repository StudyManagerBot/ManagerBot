package app.discord.listeners

import app.discord.attendance.dto.ServerMemberJoinEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import org.springframework.context.ApplicationEventPublisher

@DiscordEventHandler
class GuildVoiceUpdateListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher){

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        this.applicationEventPublisher.publishEvent(
            this.toServerMemberJoinEvent(event = event)
        )
    }


    private fun toServerMemberJoinEvent(event: GuildVoiceUpdateEvent)
            = ServerMemberJoinEvent(
        userId = event.member.user.id
    )
}