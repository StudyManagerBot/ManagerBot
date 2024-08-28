package app.discord.listeners

import app.discord.user.dto.ChangedNickNameEvent
import app.discord.user.dto.UserUpdateEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent
import org.springframework.context.ApplicationEventPublisher

@DiscordEventHandler
class GuildMemberUpdateEventListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onGuildMemberUpdate(event: GuildMemberUpdateEvent) {
        this.applicationEventPublisher.publishEvent(
            this.memberUpdate(event = event)
        )
    }

    override fun onGuildMemberUpdateNickname(event: GuildMemberUpdateNicknameEvent) {
        this.applicationEventPublisher.publishEvent(
            this.memberNicknameUpdate(event = event)
        )
    }

    private fun memberUpdate(event: GuildMemberUpdateEvent) = UserUpdateEvent(
        guildId = event.guild.idLong,
        userId = event.user.idLong,
        name = event.user.name,
        nickname = event.member.nickname
            ?: ""
    )
    private fun memberNicknameUpdate(event: GuildMemberUpdateNicknameEvent) = ChangedNickNameEvent(
        guildId = event.guild.idLong,
        memberId = event.member.idLong,
        nickname = event.member.nickname
            ?: ""
    )
}