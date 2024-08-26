package app.discord.listeners

import app.discord.user.dto.GuildMeberLeaveEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime

@DiscordEventHandler
class GuildMemberLeaveEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberRemove(event: GuildMemberRemoveEvent){
        this.applicationEventPublisher.publishEvent(
            this.memberRemove(event = event)
        )
    }

    private fun memberRemove(event: GuildMemberRemoveEvent) =
        GuildMeberLeaveEvent(
            guildId = event.guild.id,
            userId = event.user.id,
            leaveTimestamp = OffsetDateTime.now()
        )

}