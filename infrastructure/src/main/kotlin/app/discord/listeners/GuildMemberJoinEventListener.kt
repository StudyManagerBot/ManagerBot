package app.discord.listeners

import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime
import java.time.ZoneOffset

@DiscordEventHandler
class GuildMemberJoinEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        this.applicationEventPublisher.publishEvent(
            this.memberRegister(event = event)
        )
    }

    private fun memberRegister(event: GuildMemberJoinEvent) = UserRegisterEvent(
        guildId = event.guild.id,
        userId = event.user.id,
        username = event.user.name,
        globalName = event.user.globalName ?: "",
        registerTime = event.member.timeJoined
//        leaveTime = OffsetDateTime.of(0, 0, 0, 0, 0, 0, 0, ZoneOffset.of("+9"))
    )
}