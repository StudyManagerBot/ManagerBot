package app.discord.service.user

import app.discord.user.dto.*
import app.discord.user.dto.attendance.ServerMemberJoinEvent
import app.discord.user.dto.attendance.ServerMemberLeftEvent


interface UserService {

    fun registerUser(userRegisterEvent: UserRegisterEvent): UserResult
    fun updateUserInfo(userUpdateEvent: UserUpdateEvent): UserResult
    fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent): UserResult
    fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent): UserResult
//    fun registerGuildMembers(registerGuildMembers: List<UserRegisterEvent>) // command에서 UserRegisterEvent를 복수개 발생시킴.
    fun deleteAllGuildMembers(botKickedEvent: BotKickedEvent)
    fun channelJoin(serverMemberJoinEvent: ServerMemberJoinEvent)
    fun channelExit(serverMemberLeftEvent: ServerMemberLeftEvent)
}