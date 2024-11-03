package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

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
        globalName = event.user.globalName ?: "unknown",
        registerTime = event.member.timeJoined,
        nickname = event.member.nickname ?: "unknown",
        leaveTime = OffsetDateTime.of(LocalDateTime.of(1990, 1, 1, 0, 0, 0), ZoneOffset.of("+09:00"))
    )
}