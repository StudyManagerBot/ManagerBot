package app.discord.controllers

import app.discord.attendance.dto.ServerMemberJoinEvent
import app.discord.service.user.UserService
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import org.springframework.context.event.EventListener

class DiscordEventController(
    private val userService: UserService,
) {

    @EventListener(ServerMemberJoinEvent::class)
    fun handleServerJoinEvent(event: ServerMemberJoinEvent) {

    }

    @EventListener(UserRegisterEvent::class)
    fun userRegisterEvent(event: UserRegisterEvent) {
        userService.registerUser(userRegisterEvent = event)
    }

    @EventListener(UserUpdateEvent::class)
    fun userUpdateEvent(event: UserUpdateEvent) {
        userService.updateUser(userUpdateEvent = event)
    }
}