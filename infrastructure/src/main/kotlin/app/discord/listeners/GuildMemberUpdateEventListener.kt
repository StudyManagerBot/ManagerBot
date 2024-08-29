package app.discord.listeners

import app.discord.user.dto.NickNameChangedEvent
import app.discord.user.dto.UserIdentifier
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
            this.toUserUpdateEvent(event = event)
        )
    }

    override fun onGuildMemberUpdateNickname(event: GuildMemberUpdateNicknameEvent) {
        this.applicationEventPublisher.publishEvent(
            this.toNickNameChangedEvent(event = event)
        )
    }

    private fun toUserUpdateEvent(event: GuildMemberUpdateEvent) = UserUpdateEvent(
        userIdentifier = UserIdentifier(guildId = event.guild.id, userId = event.user.id),
        name = event.user.name,
        nickname = event.member.nickname ?: "",
    )
    private fun toNickNameChangedEvent(event: GuildMemberUpdateNicknameEvent) = NickNameChangedEvent(
        userIdentifier = UserIdentifier(guildId = event.guild.id, userId = event.user.id),
        nickname = event.member.nickname ?: ""
    )
}