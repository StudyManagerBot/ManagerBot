package app.discord.service.user

import app.discord.user.dto.*


interface UserService {
    fun registerUser(userRegisterEvent: UserRegisterEvent): UserResult
    fun updateUserInfo(userUpdateEvent: UserUpdateEvent): UserResult
    fun updateUserNickname(userNickNameChangedEvent: NickNameChangedEvent): UserResult
//    fun toGuildMemberLeaveEvent
    fun leaveUser(guildMemberLeaveEvent: GuildMemberLeaveEvent): UserResult
}