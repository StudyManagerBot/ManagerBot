package app.discord.listeners

import app.discord.user.dto.GuildMemberLeaveEvent
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
        GuildMemberLeaveEvent(
            guildId = event.guild.id,
            userId = event.user.id,
            leaveTime = OffsetDateTime.now()
        )

}