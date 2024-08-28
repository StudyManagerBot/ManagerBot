package app.discord.listeners

import app.discord.user.dto.MemberRegisterEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import org.springframework.context.ApplicationEventPublisher


@DiscordEventHandler
class GuildMemberJoinEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        this.applicationEventPublisher.publishEvent(
            this.memberRegister(event = event)
        )
    }

    private fun memberRegister(event: GuildMemberJoinEvent) = MemberRegisterEvent(
        guildId = event.guild.idLong,
        userId = event.user.idLong,
        username = event.user.name,
        globalName = event.user.globalName ?: "",
        registerTime = event.member.timeJoined
    )
}