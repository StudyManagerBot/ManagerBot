package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.dto.attendance.ServerMemberLeftEvent
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
        val leftChannel: AudioChannelUnion? = event.channelLeft
        val updateChannel: AudioChannelUnion? = joinChannel ?: leftChannel
        if (updateChannel != null) {
            this.applicationEventPublisher.publishEvent(
                if(joinChannel != null){this.toChannelJoinEvent(event = event, audioChannel = updateChannel)}
                else{this.toChannelLeftEvent(event = event, audioChannel = updateChannel)}
            )
        }
        else throw IllegalArgumentException("유저 Channal 정보 오류")
    }

    private fun toChannelJoinEvent(event: GuildVoiceUpdateEvent, audioChannel: AudioChannelUnion): ServerMemberJoinEvent {
        val userIdentifier = UserIdentifier(guildId = event.guild.id, userId = event.member.id)
        val userName = event.guild.selfMember.user.name
        return ServerMemberJoinEvent(
            userIdentifier = userIdentifier,
            userName = userName,
            channelId = audioChannel.id, channelName = audioChannel.name,
            joinTime = OffsetDateTime.now(),
            userRegisterEvent = UserRegisterEvent(
                userIdentifier = userIdentifier,
                userName = userName,
                globalName = event.guild.selfMember.user.globalName?:"",
                nickname = event.guild.selfMember.nickname?:"",
                registerTime = OffsetDateTime.now(),
                leaveTime = OffsetDateTime.MIN
            )
        )
    }

    private fun toChannelLeftEvent(event: GuildVoiceUpdateEvent, audioChannel: AudioChannelUnion) =
        ServerMemberLeftEvent(
            userIdentifier = UserIdentifier(guildId = event.guild.id, userId = event.member.user.id),
            userName = event.member.user.name,
            channelId = audioChannel.id, channelName = audioChannel.name,
            leftTime = OffsetDateTime.now()
        )
}