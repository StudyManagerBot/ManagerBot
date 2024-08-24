package app.discord.controllers

import app.discord.attendance.dto.ServerMemberJoinEvent
import org.springframework.context.event.EventListener

class DiscordEventController() {

    @EventListener(ServerMemberJoinEvent::class)
    fun handleServerJoinEvent(event: ServerMemberJoinEvent) {

    }
}