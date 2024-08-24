package app.discord.listeners

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter


@DiscordEventHandler
class GuildMemberJoinEventListenerAdapter(

) : ListenerAdapter(){

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {

    }
}