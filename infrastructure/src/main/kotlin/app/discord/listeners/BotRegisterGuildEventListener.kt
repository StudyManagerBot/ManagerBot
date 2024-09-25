package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime

@Deprecated(message = "첫 길드 가입시 자기자신의 멤버 정보만 가지고있어 사용보류.")
@DiscordEventHandler
class BotRegisterGuildEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) :DiscordListener(applicationEventPublisher){
    override fun onGuildJoin(event: GuildJoinEvent) =
        event.guild.members.forEach {
            this.applicationEventPublisher.publishEvent(
                this.toUserRegisterEvent(member = it)
            )
        }

    private fun toUserRegisterEvent(member: Member) = UserRegisterEvent(
        userIdentifier = UserIdentifier(guildId = member.guild.id, userId = member.user.id),
        userName = member.user.name,
        globalName = member.user.globalName ?: "",
        registerTime = member.timeJoined,
        nickname = member.nickname ?: "",
        leaveTime = OffsetDateTime.MIN,
    )
}