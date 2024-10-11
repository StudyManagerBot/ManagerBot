package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime


@DiscordEventHandler
class BotRegisterGuildEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) :DiscordListener(applicationEventPublisher){
    override fun onGuildJoin(event: GuildJoinEvent) =
        this.applicationEventPublisher.publishEvent(
            this.toBotRegisterEvent(member = event.guild.selfMember)
        )

    private fun toBotRegisterEvent(member: Member) = UserRegisterEvent(
        userIdentifier = UserIdentifier(guildId = member.guild.id, userId = member.user.id),
        userName = member.user.name,
        globalName = member.user.globalName ?: "iamBot",
        registerTime = member.timeJoined,
        nickname = member.nickname ?: "iamBot",
        leaveTime = OffsetDateTime.MIN,
    )
}