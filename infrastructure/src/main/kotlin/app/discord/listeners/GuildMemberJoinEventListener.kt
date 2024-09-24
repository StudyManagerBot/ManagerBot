package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime

@DiscordEventHandler
class GuildMemberJoinEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        this.applicationEventPublisher.publishEvent(
            this.toUserRegisterEvent(event = event)
        )
    }

    private fun toUserRegisterEvent(event: GuildMemberJoinEvent) = UserRegisterEvent(
        userIdentifier = UserIdentifier(guildId = event.guild.id, userId = event.member.user.id),
        userName = event.user.name,
        globalName = event.user.globalName ?: "",
        registerTime = event.member.timeJoined,
        nickname = event.member.nickname ?: "",
        leaveTime = OffsetDateTime.MIN,
    )
}