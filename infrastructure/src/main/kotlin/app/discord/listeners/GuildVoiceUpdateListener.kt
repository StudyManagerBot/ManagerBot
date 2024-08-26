package app.discord.listeners

import app.discord.attendance.dto.ServerMemberJoinEvent
import app.discord.attendance.dto.ServerMemberLeftEvent
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime

@DiscordEventHandler
class GuildVoiceUpdateListener(
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher){

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        val joinChannel: AudioChannelUnion? = event.channelJoined
        //FIXME
        if(joinChannel != null) this.toServerMemberJoinEvent(event = event, audioChannel = joinChannel)
    }

    private fun toServerMemberJoinEvent(event: GuildVoiceUpdateEvent, audioChannel: AudioChannelUnion) =
        ServerMemberJoinEvent(
            userId = event.member.user.id, userName = event.member.user.name,
            channelId = audioChannel.id, channelName = audioChannel.name,
            joinTime = OffsetDateTime.now()
        )

    private fun toServerMemberLeftEvent(event: GuildVoiceUpdateEvent, audioChannel: AudioChannelUnion) =
        ServerMemberLeftEvent(
            userId = event.member.user.id, userName = event.member.user.name,
            channelId = audioChannel.id, channelName = audioChannel.name,
            leftTime = OffsetDateTime.now()
        )
}