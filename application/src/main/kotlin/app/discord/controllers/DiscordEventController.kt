package app.discord.controllers

import app.discord.service.user.UserService
import app.discord.user.dto.BotKickedEvent
import app.discord.user.dto.GuildMemberLeaveEvent
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent
import app.discord.user.dto.attendance.ChannelMemberJoinEvent
import app.discord.user.dto.attendance.ChannelMemberLeftEvent
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

    @EventListener(ChannelMemberJoinEvent::class)
    fun handleServerJoinEvent(event: ChannelMemberJoinEvent) {
        userService.channelJoin(serverMemberJoinEvent = event)
    }

    @EventListener(ChannelMemberLeftEvent::class)
    fun handleServerLeftEvent(event: ChannelMemberLeftEvent) {
        userService.channelExit(serverMemberLeftEvent = event)
    }
}