package app.discord.controllers

import app.discord.service.user.UserService
import app.discord.user.dto.BotKickedEvent
import app.discord.user.dto.GuildMemberLeaveEvent
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.dto.attendance.ServerMemberLeftEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Controller

@Controller
class DiscordEventController(
    private val userService: UserService,
) {
    @EventListener(UserRegisterEvent::class)
    fun userRegisterEvent(event: UserRegisterEvent) {
        userService.registerUser(userRegisterEvent = event)
    }

    @EventListener(UserUpdateEvent::class)
    fun userUpdateEvent(event: UserUpdateEvent) {
        userService.updateUserInfo(userUpdateEvent = event)
    }

    @EventListener(GuildMemberLeaveEvent::class)
    fun guildMemberLeaveEvent(event: GuildMemberLeaveEvent) {
        userService.leaveUser(guildMemberLeaveEvent = event)
    }

    @EventListener(BotKickedEvent::class)
    fun botKickEvent(event: BotKickedEvent) {
        userService.deleteAllGuildMembers(botKickedEvent = event)
    }

    @EventListener(ServerMemberJoinEvent::class)
    fun handleServerJoinEvent(event: ServerMemberJoinEvent) {
        userService.channelJoin(serverMemberJoinEvent = event)
    }

    @EventListener(ServerMemberLeftEvent::class)
    fun handleServerLeftEvent(event: ServerMemberLeftEvent) {
        userService.channelExit(serverMemberLeftEvent = event)
    }
}