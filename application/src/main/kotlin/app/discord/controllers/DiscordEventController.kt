package app.discord.controllers

import app.discord.attendance.dto.ServerMemberJoinEvent
import app.discord.user.dto.FirstMemberJoin
import org.springframework.context.event.EventListener

class DiscordEventController() {

    @EventListener(ServerMemberJoinEvent::class)
    fun handleServerJoinEvent(event: ServerMemberJoinEvent) {

    }

    @EventListener(FirstMemberJoin::class)
    fun saveFirstJoinUserEvent(event: FirstMemberJoin) {
        
    }
}