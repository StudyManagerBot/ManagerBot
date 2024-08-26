package app.discord.listeners

import app.discord.user.dto.FirstMemberJoin
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import org.springframework.context.ApplicationEventPublisher


@DiscordEventHandler
class GuildMemberJoinEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        this.applicationEventPublisher.publishEvent(
            this.firstMemberJoin(event = event)
        )
    }

    private fun firstMemberJoin(event: GuildMemberJoinEvent) = FirstMemberJoin(
        guildId = event.guild.id,
        userId = event.user.id,
        username = event.user.name,
        globalName = event.user.globalName
            ?: "",
        firstJoinTimestamp = event.member.timeJoined
    )
}