package app.discord.service.user

import app.discord.user.dto.GuildMemberLeaveEvent
import app.discord.user.dto.NickNameChangedEvent
import app.discord.user.dto.UserRegisterEvent
import app.discord.user.dto.UserUpdateEvent


interface UserService {
    fun registerUser(userRegisterEvent: UserRegisterEvent)
    fun updateUserInfo(userUpdateEvent: UserUpdateEvent)
    fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent)
//    fun toGuildMemberLeaveEvent
    fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent)
}